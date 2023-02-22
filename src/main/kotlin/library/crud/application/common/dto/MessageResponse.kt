package library.crud.application.common.dto

import lombok.Getter
import lombok.ToString

@Getter
@ToString
class MessageResponse(val message: String, val messageType: MessageType) {
    fun hasError() : Boolean = messageType == MessageType.ERROR
}