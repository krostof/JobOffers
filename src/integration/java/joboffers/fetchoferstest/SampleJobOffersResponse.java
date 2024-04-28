package joboffers.fetchoferstest;

public interface SampleJobOffersResponse {

    default String bodyWithOneOfferJson() {
        return """
                [
                {
                    "title": "Software Engineer - Mobile (m/f/d)",
                    "company": "Cybersource",
                    "salary": "4k - 8k PLN",
                    "offerUrl": "https://nofluffjobs.com/pl/job/software-engineer-mobile-m-f-d-cybersource-poznan-entavdpn"
                }
                ]
                """.trim();
    }

    default String bodyWithTwoIdenticalOfferJson() {
        return """
                [
                {
                    "title": "Software Engineer - Mobile (m/f/d)",
                    "company": "Cybersource",
                    "salary": "4k - 8k PLN",
                    "offerUrl": "https://nofluffjobs.com/pl/job/software-engineer-mobile-m-f-d-cybersource-poznan-entavdpn"
                },
                {
                    "title": "Software Engineer - Mobile (m/f/d)",
                    "company": "Cybersource",
                    "salary": "4k - 8k PLN",
                    "offerUrl": "https://nofluffjobs.com/pl/job/software-engineer-mobile-m-f-d-cybersource-poznan-entavdpn"
                }
                ]
                """.trim();
    }

     default String bodyWithZeroOffers() {
        return "[]";
    }
}
