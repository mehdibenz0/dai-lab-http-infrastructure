package main.java;

import java.util.ArrayList;
import java.util.List;

public class QuoteRepo {
    private final List<Quote> quotes = new ArrayList<>();
    private long nextId = 1;

    public List<Quote> getAllQuotes() {
        return new ArrayList<>(quotes);
    }

    public void save(Quote quote) {
        quote.setId(String.valueOf(nextId++));
        quotes.add(quote);
    }
}
