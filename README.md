# swedishWeatherForecast
KTH Course Project ID1212

KEY FUNCTIONALITES

- User Registration/Login
- Collect weather forecast data from Swedish Meteorological and Hydrological Institute (SMHI)
- Execute Daily Crone Job to update location wise weather data. (http://localhost:8080/swforecast/update)
- Write all temperature data in JSon file to dynamically view in Google Maps (http://localhost:8080/swforecast/writejson)
- Google Maps API
- Gmail SMTP Mail Service
- REST API (http://opendata-download-metfcst.smhi.se/)
- Mobile Responsive
- Spring Framework, Hibernate, Spring Security, Bootstrap, Jquery, JavaScript, MySQL, HTML/CSS


# USER TABLE
```
CREATE TABLE `users` (
  `userid` bigint(100) NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

```

# SMHIDATA TABLE

```
CREATE TABLE `smhidata` (
  `recid` varchar(200) NOT NULL,
  `timestamp` timestamp NULL DEFAULT NULL,
  `location` varchar(200) DEFAULT NULL,
  `lat` double DEFAULT NULL,
  `lon` double DEFAULT NULL,
  `temperature` double DEFAULT NULL,
  `windspeed` double DEFAULT NULL,
  PRIMARY KEY (`recid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


```


# GEOGRAPHICAL CORRDINATES OF MAJOR CITIES OF SWEDEN TABLE

```
CREATE TABLE `smhipoints` (
  `rank` int(11) DEFAULT NULL,
  `city` varchar(50) DEFAULT NULL,
  `population` int(11) DEFAULT NULL,
  `lat` double DEFAULT NULL,
  `lon` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


```


# USER NOTIFICATION TABLE

```
CREATE TABLE `notification` (
  `n_id` double NOT NULL,
  `userid` bigint(11) DEFAULT NULL,
  `cityname` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`n_id`),
  KEY `FK_notification_userid` (`userid`),
  CONSTRAINT `FK_notification_userid` FOREIGN KEY (`userid`) REFERENCES `users` (`userid`) ON DELETE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


```
