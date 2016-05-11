package xyz.bringoff.yalantistask1.data.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Address {

    @SerializedName("id")
    @Expose
    public int id;
    @SerializedName("district")
    @Expose
    public District district;
    @SerializedName("city")
    @Expose
    public City city;
    @SerializedName("street")
    @Expose
    public Street street;
    @SerializedName("house")
    @Expose
    public House house;
    @SerializedName("flat")
    @Expose
    public String flat;

    /**
     * No args constructor for use in serialization
     */
    public Address() {
    }

    public Address(int id, District district, City city, Street street, House house, String flat) {
        this.id = id;
        this.district = district;
        this.city = city;
        this.street = street;
        this.house = house;
        this.flat = flat;
    }


    public static class City {

        @SerializedName("id")
        @Expose
        public int id;
        @SerializedName("name")
        @Expose
        public String name;
        @SerializedName("ru_name")
        @Expose
        public String ruName;

        /**
         * No args constructor for use in serialization
         */
        public City() {
        }

        public City(int id, String name, String ruName) {
            this.id = id;
            this.name = name;
            this.ruName = ruName;
        }

    }

    public static class District {

        @SerializedName("id")
        @Expose
        public int id;
        @SerializedName("name")
        @Expose
        public String name;
        @SerializedName("ru_name")
        @Expose
        public String ruName;

        /**
         * No args constructor for use in serialization
         */
        public District() {
        }

        public District(int id, String name, String ruName) {
            this.id = id;
            this.name = name;
            this.ruName = ruName;
        }

    }

    public static class House {

        @SerializedName("id")
        @Expose
        public int id;
        @SerializedName("name")
        @Expose
        public String name;

        /**
         * No args constructor for use in serialization
         */
        public House() {
        }

        public House(int id, String name) {
            this.id = id;
            this.name = name;
        }

    }

    public static class Street {

        @SerializedName("id")
        @Expose
        public int id;
        @SerializedName("name")
        @Expose
        public String name;
        @SerializedName("ru_name")
        @Expose
        public String ruName;
        @SerializedName("street_type")
        @Expose
        public StreetType streetType;

        /**
         * No args constructor for use in serialization
         */
        public Street() {
        }

        public Street(int id, String name, String ruName, StreetType streetType) {
            this.id = id;
            this.name = name;
            this.ruName = ruName;
            this.streetType = streetType;
        }

        public static class StreetType {

            @SerializedName("id")
            @Expose
            public int id;
            @SerializedName("name")
            @Expose
            public String name;
            @SerializedName("short_name")
            @Expose
            public String shortName;

            /**
             * No args constructor for use in serialization
             */
            public StreetType() {
            }

            public StreetType(int id, String name, String shortName) {
                this.id = id;
                this.name = name;
                this.shortName = shortName;
            }

        }
    }
}