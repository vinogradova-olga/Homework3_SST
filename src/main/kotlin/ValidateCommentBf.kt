package org.example


class ValidateCommentBf {

    operator fun invoke(input: Comment): ValidationStatus = when {
        input.text.length !in 3..300 ->
            ValidationStatus.Rejected(ValidationError.INVALID_TEXT)

        input.text.contains(Regex("""["';.()%]""")) ->
            ValidationStatus.Rejected(ValidationError.FORBIDDEN_CHARS)

        input.securityRatingScore != null && input.securityRatingScore !in 1..10 ->
            ValidationStatus.Rejected(ValidationError.INVALID_RATING)

        else -> ValidationStatus.Accepted
    }
}

data class Comment(
    val text: String,
    val securityRatingScore: Int?
)