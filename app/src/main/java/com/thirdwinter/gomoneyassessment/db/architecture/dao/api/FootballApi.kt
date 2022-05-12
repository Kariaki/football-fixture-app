package com.thirdwinter.gomoneyassessment.db.architecture.dao.api

import com.thirdwinter.gomoneyassessment.db.architecture.dao.api.response.*
import com.thirdwinter.gomoneyassessment.util.Constants.apiKey
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface FootballApi {

    //TODO
    /**
     * show all competitions TODO DONE
     * show matches for competitions TODO DONE
     * show teams for competitions TODO DONE
     * show squad for each teams TODO DONE
     * TODO SHOW MATCH FOR TODAY
     *
     */
    /**
     * TODO TABLES
     * MATCHES TABLE
     * COMPETITION TABLE
     * SQUAD TABLE
     *
     */


    @GET("competitions?plan=TIER_ONE")
    @Headers("X-Auth-Token: $apiKey")
    suspend fun getCompetitions(): Response<CompetitionResponse>

    @GET("competitions/{competitionId}/teams")
    @Headers("X-Auth-Token: $apiKey")
    suspend fun getTeamForCompetition(@Path("competitionId") competitionId: Int?): Response<TeamResponse>

    //TODO NUMBER ONE TEAMS

    @GET("teams/{teamId}")
    @Headers("X-Auth-Token: $apiKey")
    suspend fun getTeamSquad(@Path("teamId") teamId: Int?): Response<SquadResponse>

    @GET("competitions/{competitionId}/matches")
    @Headers("X-Auth-Token: $apiKey")
    suspend fun getMatchesForCompetition(@Path("competitionId") competitionId: Int?): Response<MatchResponse>

    @GET("competitions/{competitionId}/standings")
    @Headers("X-Auth-Token: $apiKey")
    suspend fun getTableForCompetition(@Path("competitionId") competitionId: Int?): Response<TableResponse>


    @GET("matches")
    @Headers("X-Auth-Token: $apiKey")
    suspend fun getTodayMatch(): Response<MatchResponse>

    @GET("competitions/{competitionId}/scorers")
    @Headers("X-Auth-Token: $apiKey")
    suspend fun getCompetitionScorers(@Path("competitionId") competitionId: Int?): Response<ScorersResponse>


}