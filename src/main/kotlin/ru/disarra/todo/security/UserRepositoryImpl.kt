package ru.disarra.todo.security

import org.jooq.DSLContext
import org.jooq.impl.DSL.arrayAgg
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import ru.disarra.todo.jooq.Tables
import java.util.*


@Service
class UserRepositoryImpl(
    val dslContext: DSLContext,
    val encoder: PasswordEncoder
) : UserRepository {

    override fun createUser(username: String, password: String) {
        dslContext.insertInto(Tables.USER)
            .set(Tables.USER.LOGIN, username)
            .set(Tables.USER.PASSWORDHASH, encoder.encode(password))
            .set(Tables.USER.ACTIVE, true)
            .execute()
    }

    override fun loadUserByUsername(username: String?): UserDetails {
        val groupTableForMember = Tables.GROUP.`as`(GROUP_MEMBER_ALIAS)
        val groupTableForAdmin = Tables.GROUP.`as`(GROUP_ADMIN_ALIAS)

        return dslContext.select(
            *Tables.USER.fields(),
            arrayAgg(groupTableForMember.ID).`as`(USER_GROUPS_ALIAS),
            arrayAgg(groupTableForAdmin.ID).`as`(USER_ADMIN_ALIAS)
        )
            .from(
                Tables.USER
                    .leftJoin(Tables.USER_GROUP).on(Tables.USER_GROUP.USER_ID.eq(Tables.USER.ID))
                    .leftJoin(groupTableForMember).on(Tables.USER_GROUP.GROUP_ID.eq(groupTableForMember.ID))
                    .leftJoin(groupTableForAdmin).on(Tables.USER.ID.eq(groupTableForAdmin.ADMIN_ID))
            )
            .where(Tables.USER.LOGIN.eq(username))
            .groupBy(Arrays.stream(Tables.USER.fields()).toList())
            .fetchOne { userRecord ->
                val userGroupAuthorities: Array<GrantedAuthority> =
                    (userRecord.getValue(USER_GROUPS_ALIAS) as Array<Long?>)
                        .filterNotNull()
                        .map { groupId -> SimpleGrantedAuthority(GROUP_ROLE_PREFIX + groupId.toString()) }
                        .toTypedArray()
                val userAdminAuthorities: Array<GrantedAuthority> =
                    (userRecord.getValue(USER_ADMIN_ALIAS) as Array<Long?>)
                        .filterNotNull()
                        .map { groupId ->SimpleGrantedAuthority(GROUP_ADMIN_PREFIX + groupId.toString()) }
                        .toTypedArray()
                val userAuthorities: Array<GrantedAuthority> = (userGroupAuthorities + userAdminAuthorities)

                User(
                    userRecord.getValue(Tables.USER.LOGIN),
                    userRecord.getValue(Tables.USER.PASSWORDHASH),
                    userRecord.getValue(Tables.USER.ACTIVE),
                    userRecord.getValue(Tables.USER.ACTIVE),
                    userRecord.getValue(Tables.USER.ACTIVE),
                    userRecord.getValue(Tables.USER.ACTIVE),
                    userAuthorities.toSet()
                )
            } ?: throw UsernameNotFoundException(username)
    }

    override fun getUserId(userLogin: String): Long {
        return dslContext.select(Tables.USER.ID)
            .from(Tables.USER)
            .where(Tables.USER.LOGIN.eq(userLogin))
            .fetchOne { r ->
                r.get(Tables.USER.ID)
            }!!
    }

    companion object {
        const val USER_GROUPS_ALIAS = "groups"
        const val USER_ADMIN_ALIAS = "admins"
        const val GROUP_MEMBER_ALIAS = "group_memebers"
        const val GROUP_ADMIN_ALIAS = "group_admin"
    }

}