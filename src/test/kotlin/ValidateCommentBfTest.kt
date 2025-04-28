import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import org.example.Comment
import org.example.ValidateCommentBf
import org.example.ValidationError
import org.example.ValidationStatus

class ValidateCommentBfTest : StringSpec() {

    val validateCommentBf = ValidateCommentBf()

    private val validText = "Valid text"

    init {
        """1.Should accept comment if valid text is provided and no securityRatingScore provided."""{
            val result = validateCommentBf(Comment(validText, null))
            result shouldBe ValidationStatus.Accepted
        }

        """2.Should accept comment if valid text is provided and valid securityRatingScore provided."""{
            val result = validateCommentBf(Comment(validText, 5))
            result shouldBe ValidationStatus.Accepted
        }

        """3.Should reject comment if too short text is provided."""{
            val text = "Aa"
            val result = validateCommentBf(Comment(text, null))
            result shouldBe ValidationStatus.Rejected(ValidationError.INVALID_TEXT)
        }

        """4.Should reject comment if too long text is provided."""{
            val text = "A"
            val result = validateCommentBf(Comment(text.repeat(301), null))
            result shouldBe ValidationStatus.Rejected(ValidationError.INVALID_TEXT)
        }

        """5.Should reject comment if contains forbidden characters""" {
            val text = "Test comment %"
            val result = validateCommentBf(Comment(text, null))
            result shouldBe ValidationStatus.Rejected(ValidationError.FORBIDDEN_CHARS)
        }

        """6.Should reject comment if securityRatingScore is greater than 10""" {
            val result = validateCommentBf(Comment(validText, 11))
            result shouldBe ValidationStatus.Rejected(ValidationError.INVALID_RATING)
        }

        """7.Should reject comment if invalid securityRatingScore is less then 1""" {
            val result = validateCommentBf(Comment(validText, 0))
            result shouldBe ValidationStatus.Rejected(ValidationError.INVALID_RATING)
        }
    }
}