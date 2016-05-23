package com.neu.Util;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.neu.JSONPojo.HotelJSON;
import com.neu.JSONPojo.JSONDetails;
import com.neu.JSONPojo.LocationJSON;
import com.neu.JSONPojo.PriceJSON;
import com.neu.JSONPojo.StayDatesJSON;
import com.neu.JSONPojo.ValueJSON;

public class Parse {

	public JSONDetails getJSONData(String json) {
		Type type = new TypeToken<Map<String, Object>>() {
		}.getType();
		Gson gson = new Gson();
		Map<String, Object> treeViewMap = gson.fromJson(json.trim(), type);
		JSONDetails jsondet = new JSONDetails();
		StayDatesJSON staydates = new StayDatesJSON();
		ArrayList<HotelJSON> hotellist = new ArrayList();
		jsondet.setHotelList(hotellist);
		for (Map.Entry<String, Object> entry : treeViewMap.entrySet()) {
			if (!entry.getKey().equalsIgnoreCase("HotelInfoList")) {
				if (entry.getKey().equalsIgnoreCase("MatchingHotelCount")) {
					jsondet.setMatchingHotelCount(Integer.parseInt(entry.getValue().toString()));
				} else if (entry.getKey().equalsIgnoreCase("StayDates")) {
					Map stayDateMap = (Map) entry.getValue();
					Iterator itr = stayDateMap.entrySet().iterator();
					while (itr.hasNext()) {
						Map.Entry stayDateMapEntry = (Map.Entry) itr.next();
						if (stayDateMapEntry.getKey().toString().equalsIgnoreCase("CheckInDate")) {
							staydates.setCheckInDate(stayDateMapEntry.getValue().toString());
						} else if (stayDateMapEntry.getKey().toString().equalsIgnoreCase("CheckOutDate")) {
							staydates.setCheckOutDate(stayDateMapEntry.getValue().toString());
						}
					}
				}
				jsondet.setStayDates(staydates);

			} else {

				Map hotelInfoListMap = (Map) entry.getValue();
				Iterator itr = hotelInfoListMap.entrySet().iterator();
				while (itr.hasNext()) {
					Map.Entry hotelInfoListEntry = (Map.Entry) itr.next();
					if (hotelInfoListEntry.getKey().toString().equalsIgnoreCase("HotelInfo")) {

						ArrayList<Map> hotels = (ArrayList<Map>) hotelInfoListEntry.getValue();
						// System.out.println(hotels.toString());

						for (Map hotel : hotels) {
							Iterator itr1 = hotel.entrySet().iterator();
							HotelJSON hoteljson = new HotelJSON();
							jsondet.getHotelList().add(hoteljson);
							while (itr1.hasNext()) {

								Map.Entry hotelInfoMapEntry = (Map.Entry) itr1.next();
								if (hotelInfoMapEntry.getKey().toString().equals("HotelID")) {
									hoteljson.setHotelID(Integer.parseInt(hotelInfoMapEntry.getValue().toString()));
								}
								if (hotelInfoMapEntry.getKey().toString().equals("Name")) {
									hoteljson.setHotelName(hotelInfoMapEntry.getValue().toString());
								}
								if (hotelInfoMapEntry.getKey().toString().equals("Description")) {
									hoteljson.setDescription(hotelInfoMapEntry.getValue().toString());
								}
								if (hotelInfoMapEntry.getKey().toString().equals("DetailsUrl")) {
									hoteljson.setDetailsurl(hotelInfoMapEntry.getValue().toString());
								}
								if (hotelInfoMapEntry.getKey().toString().equals("GuestRating")) {
									hoteljson.setRating(hotelInfoMapEntry.getValue().toString());
								}
								if (hotelInfoMapEntry.getKey().toString().equals("ThumbnailUrl")) {
									hoteljson.setThumbnailUrl(hotelInfoMapEntry.getValue().toString());
								}
								if (hotelInfoMapEntry.getKey().toString().equals("Location")) {
									Map locationmap = (Map) hotelInfoMapEntry.getValue();
									LocationJSON location = new LocationJSON();
									Iterator locitr = locationmap.entrySet().iterator();
									while (locitr.hasNext()) {
										Map.Entry locationentry = (Map.Entry) locitr.next();
										if (locationentry.getKey().toString().equalsIgnoreCase("StreetAddress")) {
											location.setStreetAddress(locationentry.getValue().toString());

										}
										if (locationentry.getKey().toString().equalsIgnoreCase("City")) {
											location.setCity(locationentry.getValue().toString());

										}
										if (locationentry.getKey().toString().equalsIgnoreCase("Province")) {
											location.setProvince(locationentry.getValue().toString());

										}
										if (locationentry.getKey().toString().equalsIgnoreCase("Country")) {
											location.setCountry(locationentry.getValue().toString());

										}

									}
									hoteljson.setLocation(location);

								}
								if (hotelInfoMapEntry.getKey().toString().equals("Price")) {
									Map pricemap = (Map) hotelInfoMapEntry.getValue();
									Iterator priceitr = pricemap.entrySet().iterator();
									PriceJSON pricejson = new PriceJSON();
									while (priceitr.hasNext()) {
										Map.Entry priceentry = (Map.Entry) priceitr.next();
										if (priceentry.getKey().toString().equalsIgnoreCase("TotalRate")) {
											Map valuemap = (Map) priceentry.getValue();
											ValueJSON value = new ValueJSON();
											Iterator valitr = valuemap.entrySet().iterator();
											while (valitr.hasNext()) {

												Map.Entry valueentry = (Map.Entry) valitr.next();
												if (valueentry.getKey().toString().equalsIgnoreCase("Currency")) {
													value.setCurrency(valueentry.getValue().toString());
												}
												if (valueentry.getKey().toString().equalsIgnoreCase("Value")) {
													value.setValue(valueentry.getValue().toString());
												}
											}
											pricejson.setValue(value);
											hoteljson.setPrice(pricejson);
										}

									}
								}

							}
						}
					}

				}

			}

		}
		return jsondet;
	}

}
