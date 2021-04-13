package br.com.rollo.rafael.casadocodigoapi.domain.books;

public enum BookType {
    EBOOK {
        @Override
        public String toString() {
            return "eBook";
        }
    },
    HARDCOVER {
        @Override
        public String toString() {
            return "Hardcover";
        }
    },
    COMBO {
        @Override
        public String toString() {
            return "eBook + Hardcover";
        }
    };
}
