package com.example.ecss.medicalmapper.network.retrofit;


import com.example.ecss.medicalmapper.model.PlacesResponse;
import com.example.ecss.medicalmapper.model.ReviewRequest;
import com.example.ecss.medicalmapper.model.user.Doctor;
import com.example.ecss.medicalmapper.network.ApiCallStatus;
import com.example.ecss.medicalmapper.network.CommentsResponse;
import com.example.ecss.medicalmapper.network.GetPremiumDataResponse;
import com.example.ecss.medicalmapper.network.SavePlaceRequest;
import com.example.ecss.medicalmapper.network.addPlaceApiCall.AddPlaceRequest;
import com.example.ecss.medicalmapper.network.advancedSearchApiCall.AdvancedSearchRequest;
import com.example.ecss.medicalmapper.network.advancedSearchApiCall.AdvancedSearchResponse;
import com.example.ecss.medicalmapper.network.placesSearchApiCall.PlacesSearchResponse;
import com.example.ecss.medicalmapper.network.showNearbyPlacesApiCall.ShowNearbyPlacesRequest;
import com.example.ecss.medicalmapper.network.signInApiCall.SignInRequest;
import com.example.ecss.medicalmapper.network.signInApiCall.SignInResponse;
import com.example.ecss.medicalmapper.network.signUpApiCall.SignUpRequest;
import com.example.ecss.medicalmapper.network.signUpApiCall.SignUpResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface ApiInterface {
    // Functions calls
    @POST("user/signin")
    Call<SignInResponse> SignIn(@Body SignInRequest request);

    @GET("user/get/doctor/info")
    Call<GetPremiumDataResponse> GetPremiumData(@Query("user_id") Integer userId);

    @POST("user/signup")
    Call<SignUpResponse> SignUp(@Body SignUpRequest request);

    @POST("user/signup/doctor")
    Call<ApiCallStatus> DoctorSignUp(@Body Doctor request);

    @POST("user/edit/profile")
    Call<ApiCallStatus> DoctorEditProfile(@Body Doctor request);

    @POST("search/nearby_places")
    Call<PlacesResponse> ShowNearbyPlaces(@Body ShowNearbyPlacesRequest request);

    @GET("user/get/saved/place")
    Call<PlacesResponse> GetSavedPlaces(@Query("user_id") Integer userId);

    @GET("search/place/name/{placename}")
    Call<PlacesSearchResponse> PlacesSearch(@Path("placename") String placeName);

    @POST("search/filter")
    Call<AdvancedSearchResponse> AdvancedSearch(@Body AdvancedSearchRequest request);

    @POST("user/add/new/medical/place")
    Call<ApiCallStatus> AddPlace(@Body AddPlaceRequest request);

    @GET("user/get/branch/comments")
    Call<CommentsResponse> GetBranchComments(@Query("place_id") Integer placeId);

    @POST("user/add/review/rate")
    Call<ApiCallStatus> AddReview(@Body ReviewRequest reviewRequest);

    @POST("user/add/new/saved/place")
    Call<ApiCallStatus> SavePlace(@Body SavePlaceRequest savePlaceRequest);

    @POST("user/remove/saved/place")
    Call<ApiCallStatus> UnSavePlace(@Body SavePlaceRequest savePlaceRequest);


}
