package zad1;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

/*
 * Biuro podróży otrzymuje od róznych kontrahentów (polskich, angielskich, niemieckich... ) 
 * pliki z ofertami wyjazdów-wycieczek.
 *  Każda oferta jest w jednym wierszu pliku i zawiera, rozdzielone znakami tabulacji:

lokalizacje_kontrahenta kraj  date_wyjazdu  date_powrotu miejsce cene symbol_waluty

gdzie:
lokalizacja - napis,  oznaczający język_kraj (np. pl_PL, en_US; tak jak zwraca to metoda toString() z klasy Locale)
kraj - nazwa kraju w języku kontrahenta,
daty - (wyjazdu, powrotu) daty w formacie RRRR-MM-DD (np. 2015-12-31),
miejsce - jedno z: [morze, jezioro, góry] - w języku kontrahenta,
cena - liczba w formacie liczb, używanym w kraji kontrahenta,
symbol_waluty = PLN, USD itp.
 */
public class TravelDataEntry {

	private Locale locale;
	private String countryISO;
	private Calendar dateFrom;
	private Calendar dateTo;
	private String destination;
	private double price;
	private String currency;
	
	public TravelDataEntry(String[] fileRow) {
		locale = parseLanguage(fileRow[0]);
		countryISO = parseCountryISO(fileRow[1]);
	}

	private String parseCountryISO(String countryName) {
		//String[] countryCodes = Locale.getISOCountries();
		//Locale.setDefault(newLocale);
		System.out.println("using locale: " + this.locale);
		Locale.setDefault(this.locale);
		Locale[] locales = Locale.getAvailableLocales();
		for (Locale locale : locales){
            String country = locale.getDisplayCountry(this.locale);
            if (country.equals(countryName)){
            	return locale.getISO3Country();
            }
            System.out.println(country);
        }
		throw new RuntimeException("cannot find country: " + countryName);
	}

	private Locale parseLanguage(String languageTag){
		String lang = "";
		String country = "";
		if(languageTag.length()<4){
			lang = languageTag;
			country = languageTag.toUpperCase();
		}
		else{
			lang = languageTag.split("_")[0];
			country = languageTag.split("_")[1];
		}
		return new Locale(lang, country);
	}

	/**
	 * @return the locale
	 */
	public Locale getLocale() {
		return locale;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return countryISO;
	}

	/**
	 * @return the dateFrom
	 */
	public Calendar getDateFrom() {
		return dateFrom;
	}

	/**
	 * @return the dateTo
	 */
	public Calendar getDateTo() {
		return dateTo;
	}

	/**
	 * @return the destination
	 */
	public String getDestination() {
		return destination;
	}

	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @return the currency
	 */
	public String getCurrency() {
		return currency;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(String countryISO) {
		this.countryISO = countryISO;
	}

	/**
	 * @param dateFrom the dateFrom to set
	 */
	public void setDateFrom(Calendar dateFrom) {
		this.dateFrom = dateFrom;
	}

	/**
	 * @param dateTo the dateTo to set
	 */
	public void setDateTo(Calendar dateTo) {
		this.dateTo = dateTo;
	}

	/**
	 * @param destination the destination to set
	 */
	public void setDestination(String destination) {
		this.destination = destination;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * @param currency the currency to set
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}

	/**
	 * @param locale the locale to set
	 */
	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	private String countryToString(String loc){
		Locale lang = parseLanguage(loc);
		System.out.println("lang"+lang.toString());
		Locale country = new Locale("", this.countryISO);
		System.out.println("country "+country.toString());
		String countryName = country.getDisplayCountry(lang); 
		System.out.println("country name"+countryName.toString());
		return countryName;		
	}
	
	public String toString(String loc, String dateFormat) {
		//Japonia 2015-09-01 2015-10-01 jezioro 10 000,2 PLN
		return countryToString(loc)+ " "+ dateFrom + " "+dateTo+ " "+destination+ " "+price+ " "+currency;
	}
	
	
	
}
