package ch.api;
import io.javalin.*;
import io.javalin.http.Context;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Api {
    private static Map<String, Quote> quotesMap = new HashMap<>();

    public static void main(String[] args) {
        Javalin app = Javalin.create();

        app.before(ctx -> ctx.header("Access-Control-Allow-Origin", "*"));
        //QuoteRepo quoteRepository = new QuoteRepo();

        app.get("/quotes", Api::getAllQuotes);
        app.post("/quotes", Api::createQuote);
        app.get("/quotes/{id}", Api::getQuoteById);
        app.put("/quotes/{id}", Api::updateQuote);
        app.delete("/quotes/{id}", Api::deleteQuote);
        app.start(8080);
    }
    private static void getAllQuotes(Context ctx) {
        ctx.json(new ArrayList<>(quotesMap.values()));
    }

    private static void createQuote(Context ctx) {
        Quote newQuote = ctx.bodyAsClass(Quote.class);
        newQuote.setId(Integer.toString(quotesMap.size() + 1));
        quotesMap.put(newQuote.getId(), newQuote);
        ctx.json(newQuote);
    }

    private static void getQuoteById(Context ctx) {
        String quoteId = ctx.pathParam("id");
        Quote quote = quotesMap.get(quoteId);
        if (quote != null) {
            ctx.json(quote);
        } else {
            ctx.status(404).result("Quote not found");
        }
    }

    private static void updateQuote(Context ctx) {
        String quoteId = ctx.pathParam("id");
        Quote existingQuote = quotesMap.get(quoteId);
        if (existingQuote != null) {
            Quote updatedQuote = ctx.bodyAsClass(Quote.class);
            updatedQuote.setId(quoteId);
            quotesMap.put(quoteId, updatedQuote);
            ctx.json(updatedQuote);
        } else {
            ctx.status(404).result("Quote not found");
        }
    }

    private static void deleteQuote(Context ctx) {
        String quoteId = ctx.pathParam("id");
        Quote deletedQuote = quotesMap.remove(quoteId);
        if (deletedQuote != null) {
            ctx.json(deletedQuote);
        } else {
            ctx.status(404).result("Quote not found");
        }
    }
    
}
