package br.com.rollo.rafael.casadocodigoapi.domain.books;

enum BookType {
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
