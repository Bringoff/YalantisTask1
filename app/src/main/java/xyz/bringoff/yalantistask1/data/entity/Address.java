package xyz.bringoff.yalantistask1.data.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Address {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("district")
    @Expose
    private District district;
    @SerializedName("city")
    @Expose
    private City city;
    @SerializedName("street")
    @Expose
    private Street street;
    @SerializedName("house")
    @Expose
    private House house;
    @SerializedName("flat")
    @Expose
    private String flat;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Street getStreet() {
        return street;
    }

    public void setStreet(Street street) {
        this.street = street;
    }

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    public String getFlat() {
        return flat;
    }

    public void setFlat(String flat) {
        this.flat = flat;
    }

    public static class City {

        @SerializedName("id")
        @Expose
        private int id;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("ru_name")
        @Expose
        private String ruName;

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

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getRuName() {
            return ruName;
        }

        public void setRuName(String ruName) {
            this.ruName = ruName;
        }
    }

    public static class District {

        @SerializedName("id")
        @Expose
        private int id;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("ru_name")
        @Expose
        private String ruName;

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

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getRuName() {
            return ruName;
        }

        public void setRuName(String ruName) {
            this.ruName = ruName;
        }
    }

    public static class House {

        @SerializedName("id")
        @Expose
        private int id;
        @SerializedName("name")
        @Expose
        private String name;

        /**
         * No args constructor for use in serialization
         */
        public House() {
        }

        public House(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class Street {

        @SerializedName("id")
        @Expose
        private int id;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("ru_name")
        @Expose
        private String ruName;
        @SerializedName("street_type")
        @Expose
        private StreetType streetType;

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

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getRuName() {
            return ruName;
        }

        public void setRuName(String ruName) {
            this.ruName = ruName;
        }

        public StreetType getStreetType() {
            return streetType;
        }

        public void setStreetType(StreetType streetType) {
            this.streetType = streetType;
        }

        public static class StreetType {

            @SerializedName("id")
            @Expose
            private int id;
            @SerializedName("name")
            @Expose
            private String name;
            @SerializedName("short_name")
            @Expose
            private String shortName;

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

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getShortName() {
                return shortName;
            }

            public void setShortName(String shortName) {
                this.shortName = shortName;
            }
        }
    }
}