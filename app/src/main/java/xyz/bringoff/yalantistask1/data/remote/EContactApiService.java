package xyz.bringoff.yalantistask1.data.remote;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;
import xyz.bringoff.yalantistask1.data.remote.entity.TicketEntity;

public interface EContactApiService {

    @GET("tickets")
    Observable<List<TicketEntity>> getTickets(
            @Query("offset") int offset, @Query("amount") int amount);

    @GET("tickets")
    Observable<List<TicketEntity>> getTickets(
            @Query("state") String state, @Query("offset") int offset, @Query("amount") int amount);
}
