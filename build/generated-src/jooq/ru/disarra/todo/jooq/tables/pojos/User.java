/*
 * This file is generated by jOOQ.
 */
package ru.disarra.todo.jooq.tables.pojos;


import java.io.Serializable;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private final Long id;
    private final String login;
    private final String passwordhash;
    private final Boolean active;

    public User(User value) {
        this.id = value.id;
        this.login = value.login;
        this.passwordhash = value.passwordhash;
        this.active = value.active;
    }

    public User(
        Long id,
        String login,
        String passwordhash,
        Boolean active
    ) {
        this.id = id;
        this.login = login;
        this.passwordhash = passwordhash;
        this.active = active;
    }

    /**
     * Getter for <code>public.user.id</code>.
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Getter for <code>public.user.login</code>.
     */
    public String getLogin() {
        return this.login;
    }

    /**
     * Getter for <code>public.user.passwordhash</code>.
     */
    public String getPasswordhash() {
        return this.passwordhash;
    }

    /**
     * Getter for <code>public.user.active</code>.
     */
    public Boolean getActive() {
        return this.active;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final User other = (User) obj;
        if (this.id == null) {
            if (other.id != null)
                return false;
        }
        else if (!this.id.equals(other.id))
            return false;
        if (this.login == null) {
            if (other.login != null)
                return false;
        }
        else if (!this.login.equals(other.login))
            return false;
        if (this.passwordhash == null) {
            if (other.passwordhash != null)
                return false;
        }
        else if (!this.passwordhash.equals(other.passwordhash))
            return false;
        if (this.active == null) {
            if (other.active != null)
                return false;
        }
        else if (!this.active.equals(other.active))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
        result = prime * result + ((this.login == null) ? 0 : this.login.hashCode());
        result = prime * result + ((this.passwordhash == null) ? 0 : this.passwordhash.hashCode());
        result = prime * result + ((this.active == null) ? 0 : this.active.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("User (");

        sb.append(id);
        sb.append(", ").append(login);
        sb.append(", ").append(passwordhash);
        sb.append(", ").append(active);

        sb.append(")");
        return sb.toString();
    }
}
