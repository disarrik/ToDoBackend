package ru.disarra.todo.group

import org.jooq.DSLContext
import org.jooq.impl.DSL.value
import org.springframework.stereotype.Repository
import ru.disarra.todo.jooq.Tables

@Repository
class JooqGroupRepository(val dslContext: DSLContext): GroupRepository {

    override fun createGroup(group: Group): Long {
        return dslContext.insertInto(Tables.GROUP, Tables.GROUP.NAME, Tables.GROUP.ADMIN_ID)
            .select(
                dslContext.select(value(group.name), Tables.USER.ID).from(Tables.USER)
                    .where(Tables.USER.LOGIN.eq(group.adminLogin))
            )
            .returning()
            .fetchOne(Tables.GROUP.ID)!!
    }

    override fun addToGroup(groupId: Long, userId: Long) {
        dslContext.insertInto(Tables.USER_GROUP)
            .set(Tables.USER_GROUP.GROUP_ID, groupId)
            .set(Tables.USER_GROUP.USER_ID, userId)
            .execute()
    }

    override fun getGroups(userId: Long): List<Group> {
        return dslContext.select(*Tables.GROUP.fields(), Tables.USER.LOGIN)
            .from(Tables.GROUP
                .join(Tables.USER_GROUP).on(Tables.USER_GROUP.GROUP_ID.eq(Tables.GROUP.ID))
                .join(Tables.USER).on(Tables.USER_GROUP.USER_ID.eq(Tables.USER.ID)))
            .where(Tables.USER_GROUP.USER_ID.eq(userId))
            .fetch { r ->
                Group(
                    id = r.get(Tables.GROUP.ID),
                    name = r.get(Tables.GROUP.NAME),
                    adminLogin = r.get(Tables.USER.LOGIN)
                )
            }
    }

}