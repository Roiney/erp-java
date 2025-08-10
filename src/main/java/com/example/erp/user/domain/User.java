package com.example.erp.user.domain;

import jakarta.persistence.*;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(
        name = "users",
        schema = "public",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_users_email", columnNames = {"email"}),
                @UniqueConstraint(name = "uk_users_contact_id", columnNames = {"contact_id"})
        }
)
public class User {

    @Id
    @Column(name = "id", nullable = false, columnDefinition = "uuid")
    private UUID id;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "email", nullable = false, length = 255)
    private String email;

    @Column(name = "password", nullable = false, length = 255)
    private String password; // bcrypt/argon2 hash

    // relação opcional 1:1 (na prática FK única)
    @Column(name = "contact_id", columnDefinition = "uuid", unique = true)
    private UUID contactId;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false, length = 50)
    private Role role = Role.EMPLOYEE;

    @Column(name = "is_active", nullable = false)
    private boolean isActive = true;

    @Column(name = "canceled_at")
    private OffsetDateTime canceledAt;

    @Column(name = "last_login")
    private OffsetDateTime lastLogin;

    @Column(name = "is_email_validated", nullable = false)
    private boolean isEmailValidated = false;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private OffsetDateTime updatedAt;

    protected User() { }

    public User(UUID id, String name, String email, String password, UUID contactId,
                Role role, boolean isActive, OffsetDateTime canceledAt, OffsetDateTime lastLogin,
                boolean isEmailValidated, OffsetDateTime createdAt, OffsetDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.contactId = contactId;
        this.role = role;
        this.isActive = isActive;
        this.canceledAt = canceledAt;
        this.lastLogin = lastLogin;
        this.isEmailValidated = isEmailValidated;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // getters/setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public UUID getContactId() { return contactId; }
    public void setContactId(UUID contactId) { this.contactId = contactId; }

    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }

    public boolean isActive() { return isActive; }
    public void setActive(boolean active) { isActive = active; }

    public OffsetDateTime getCanceledAt() { return canceledAt; }
    public void setCanceledAt(OffsetDateTime canceledAt) { this.canceledAt = canceledAt; }

    public OffsetDateTime getLastLogin() { return lastLogin; }
    public void setLastLogin(OffsetDateTime lastLogin) { this.lastLogin = lastLogin; }

    public boolean isEmailValidated() { return isEmailValidated; }
    public void setEmailValidated(boolean emailValidated) { isEmailValidated = emailValidated; }

    public OffsetDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(OffsetDateTime createdAt) { this.createdAt = createdAt; }

    public OffsetDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(OffsetDateTime updatedAt) { this.updatedAt = updatedAt; }

    // equals/hashCode só por id
    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User that)) return false;
        return id != null && id.equals(that.id);
    }
    @Override public int hashCode() { return 31; }
}
