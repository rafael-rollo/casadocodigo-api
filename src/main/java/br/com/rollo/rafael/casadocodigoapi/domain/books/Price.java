package br.com.rollo.rafael.casadocodigoapi.domain.books;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Embeddable
class Price {

    @Column(scale = 2)
    @NotNull
    @DecimalMin("10.00")
    @Digits(integer = 3, fraction=2)
    private BigDecimal value;

    @Enumerated(EnumType.STRING)
    @NotNull
    private BookType bookType;

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public BookType getBookType() {
        return bookType;
    }

    public void setBookType(BookType bookType) {
        this.bookType = bookType;
    }
}
