package joboffers;

public interface SampleJobOffersResponse {
     default String bodyWithZeroOffers() {
        return "[]";
    }
}
