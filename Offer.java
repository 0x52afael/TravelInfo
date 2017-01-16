public class Offer {
    
    private String campaignName;
    private String destinationName;
    private String cityName;
    private String currentPrice;
    private String originalPrice;
    private String priceCurrency;
    private String hotelName;
    private String hotelImage;
    private String hotelGrade;
    private String outDate;
    private String roomDesc;
    private String journeyLen;
    private String noSeatsRemaining;
    private String departName;

    /**
     * returns the departure of the offer
     * @return returns the departure name
     */
    public String getDepartName() {
	return departName;
    }

    /**
     * sets the departure name of the offer
     * @param departName the string to set the departure name
     */
    public void setDepartName(String departName) {
	this.departName = departName;
    }

    /**
     * gets the journey lenght of the travel.
     * @return a string containing the amount of days
     */
    public String getJourneyLen() {
	return journeyLen;
    }

    /**
     * sets the journey lenght of the journlenght offer attribute
     * @param journeyLen the string to set the attribute
     */
    public void setJourneyLen(String journeyLen) {
	this.journeyLen = journeyLen;
    }

    /**
     * sets the nr of remaining seat string of offer attribute
     * @param noSeatsRemaining the string that will set the attribute
     */
    public void setNoSeatsRemaining(String noSeatsRemaining) {
	this.noSeatsRemaining = noSeatsRemaining;
    }
    
    /**
     * sets the roomdescription string of offer
     * @param roomDesc the string to set the value of the attribute
     */
    public void setRoomDesc(String roomDesc) {
	this.roomDesc = roomDesc;
    }
    /**
     * gets the string representative of nr of seats left
     * @return the string that shows nr of seats remaining
     */
    public String getNoSeatsRemaining() {
	return noSeatsRemaining;
    }
    /**
     * gets the room description of the hotel
     * @return the string showing the room description
     */
    public String getRoomDesc() {
	return roomDesc;
    }

    /**
     * gets the date the travel is.
     * @return returns the date of when the flight takes off
     */
    public String getOutDate() {
	return outDate;
    }
    /**
     * sets the date of when the flight takes off
     * @param outDate the attribute used to set the outDate
     */
    public void setOutDate(String outDate) {
	this.outDate = outDate;
    }
    
    /**
     * gets the campaign name
     * @return the campaign name string
     */
    public String getCampaignName() {
	return campaignName;
    }
    /**
     * gets the city name
     * @return the city name string
     */
    public String getCityName() {
	return cityName;
    }
    /**
     * gets the current price
     * @return the current price string
     */
    public String getCurrentPrice() {
	return currentPrice;
    }
    /**
     * gets the destination name
     * @return the destination name string
     */
    public String getDestinationName() {
	return destinationName;
    }
    /**
     * gets the hotel grade
     * @return the hotel grade string
     */
    public String getHotelGrade() {
	return hotelGrade;
    }
    /**
     * get the hotel image link
     * @return the hotel image link
     */
    public String getHotelImage() {
	return hotelImage;
    }
    /**
     * gets the hotel name
     * @return the hotel name string
     */
    public String getHotelName() {
	return hotelName;
    }
    /**
     * gets the original price
     * @return the original price string
     */
    public String getOriginalPrice() {
	return originalPrice;
    }
    /**
     * gets the price currency
     * @return the price currency string
     */
    public String getPriceCurrency() {
	return priceCurrency;
    }
    /**
     * sets the campaign name
     * @param CampaignName the string which sets the campaign name
     */
    public void setCampaignName(String CampaignName) {
	this.campaignName = CampaignName;
    }
    /**
     * sets the city name
     * @param CityName the string which sets the name
     */
    public void setCityName(String CityName) {
	this.cityName = CityName;
    }
    /**
     * sets the current price
     * @param CurrentPrice the string that sets the value
     */
    public void setCurrentPrice(String CurrentPrice) {
	this.currentPrice = CurrentPrice;
    }
    /**
     * sets the destination name
     * @param DestinationName the string that sets the value
     */
    public void setDestinationName(String DestinationName) {
	this.destinationName = DestinationName;
    }
    /**
     * sets the hotel grade
     * @param HotelGrade the string that sets the value
     */
    public void setHotelGrade(String HotelGrade) {
	this.hotelGrade = HotelGrade;
    }
    /**
     * sets the hotel image link
     * @param HotelImage the string that sets the link
     */
    public void setHotelImage(String HotelImage) {
	this.hotelImage = HotelImage;
    }
    /**
     * sets the hotel name
     * @param HotelName the string that sets the name
     */
    public void setHotelName(String HotelName) {
	this.hotelName = HotelName;
    }
    /**
     * sets the original price
     * @param OriginalPrice the string that sets the o-price
     */
    public void setOriginalPrice(String OriginalPrice) {
	this.originalPrice = OriginalPrice;
    }
    /**
     * sets the price currency
     * @param PriceCurrency the string that sets the currency
     */
    public void setPriceCurrency(String PriceCurrency) {
	this.priceCurrency = PriceCurrency;
    }

}
