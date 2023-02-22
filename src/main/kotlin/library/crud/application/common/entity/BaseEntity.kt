package library.crud.application.common.entity

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import java.util.Objects

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Version
    var version: Long? = null

    @CreatedDate
    var creationDate: LocalDateTime? = null

    @LastModifiedDate
    var lastModifiedDate: LocalDateTime? = null

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false
        val that : BaseEntity = other as BaseEntity
        return Objects.equals(id, that.id)
    }

    override fun hashCode(): Int = Objects.hash(id)
}