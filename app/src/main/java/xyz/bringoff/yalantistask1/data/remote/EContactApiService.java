package xyz.bringoff.yalantistask1.data.remote;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;
import xyz.bringoff.yalantistask1.data.entity.Ticket;

public interface EContactApiService {

    @GET("tickets")
    Observable<List<Ticket>> getTickets();

    @GET("tickets")
    Observable<List<Ticket>> getTickets(@Query("state") String state);
}
