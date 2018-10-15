-- Lisame vaikimisi riigid, mis rakenduse käivitamisel automaatselt lisatakse
-- http://localhost:8080/h2-console,  JDBC URL: jdbc:h2:mem:testdb
-- https://docs.spring.io/spring-boot/docs/current/reference/html/howto-database-initialization.html
insert into Country (currency, countryname) values('SEK', 'Rootsi');
insert into Country (currency, countryname) values('DKK', 'Taani');
insert into Country (currency, countryname) values('EUR', 'Soome');
insert into Country (currency, countryname) values('PLN', 'Poola');
insert into Country (currency, countryname) values('EUR', 'Eesti');


insert into Client (id,address,countryname, firstName, lastName,phone,secnumber) values(1,'Uppsala','Rootsi','Olle', 'Svensson','253725283','762357252');
insert into Client (id,address,countryname, firstName, lastName,phone,secnumber) values(2,'Poznan','Poola','Gzrzegozs', 'Zvyblwzki','9235823','2834737883');
insert into Client (id,address,countryname, firstName, lastName,phone,secnumber) values(3,'Krakow','Poola','Jyszctuna', 'Kowalscyk','287428','234725472');
insert into Client (id,address,countryname, firstName, lastName,phone,secnumber) values(4,'Copenhagen','Taani','Harald', 'Hokennson','3482424','982345825842');
insert into Client (id,address,countryname, firstName, lastName,phone,secnumber) values(5,'Oulu','Soome','Pekka', 'Velimäkki','3582538424','925283443');
insert into Client (id,address,countryname, firstName, lastName,phone,secnumber) values(6,'Pärnu','Eesti','Jaak', 'Tamm','825234424','98273462342');

insert into Product (id,name,barcode, description, releasedate, baseprice) values(1,'Tupla','76232332','Šokolaadibatoon','2018-03-03', '0.52');
insert into Product (id,name,barcode, description, releasedate, baseprice) values(2,'Bounty','72232332','Šokolaadibatoon','2017-11-23', '0.57');
insert into Product (id,name,barcode, description, releasedate, baseprice) values(3,'Kelluke','272232332','Limonaad','2018-07-23', '0.55');
insert into Product (id,name,barcode, description, releasedate, baseprice) values(4,'7Up','572232332','Limonaad','2018-01-13', '0.59');
insert into Product (id,name,barcode, description, releasedate, baseprice) values(5,'A le Coq Premium','7422232332','Õlu','2018-06-03', '1.57');
insert into Product (id,name,barcode, description, releasedate, baseprice) values(6,'Tamula Vanake','7242232332','Õlu','2018-03-12', '1.27');


insert into Orders (id,amount,clientid, cpprice, transactiondate, productid,currencyrate) values(1,3.0, 4,4.5,'2018-09-03', 3, 0.412330);
insert into Orders (id,amount,clientid, cpprice, transactiondate, productid,currencyrate) values(2,2.0, 1,4.5,'2018-09-04', 2, 0.83435);
insert into Orders (id,amount,clientid, cpprice, transactiondate, productid,currencyrate) values(3,3.0, 2,7.5,'2018-09-05', 1, 0.23435);
insert into Orders (id,amount,clientid, cpprice, transactiondate, productid,currencyrate) values(4,2.0 ,3,14.5,'2018-09-06', 4,0.23415);
insert into Orders (id,amount,clientid, cpprice, transactiondate, productid,currencyrate) values(5,3.0 ,5,4.5,'2018-09-07', 3, 1.0);
insert into Orders (id,amount,clientid, cpprice, transactiondate, productid,currencyrate) values(6,2.0 ,6,8.5,'2018-09-08', 5, 1.0);

insert into Orders (id,amount,clientid, cpprice, transactiondate, productid,currencyrate) values(7,4.0 ,5,12.5,'2018-09-13', 6,1.0);
insert into Orders (id,amount,clientid, cpprice, transactiondate, productid,currencyrate) values(8,1.0 ,5,2.5,'2018-09-14', 3,1.0);
insert into Orders (id,amount,clientid, cpprice, transactiondate, productid,currencyrate) values(9,3.0 ,4,8.5,'2018-09-15', 4,0.134443);
insert into Orders (id,amount,clientid, cpprice, transactiondate, productid,currencyrate) values(10,3.0 ,2,4.5,'2018-09-16', 1, 0.24125);
insert into Orders (id,amount,clientid, cpprice, transactiondate, productid,currencyrate) values(11,5.0 ,3,14.5,'2018-09-17', 2, 0.23435);
insert into Orders (id,amount,clientid, cpprice, transactiondate, productid,currencyrate) values(12,3.0 ,1,2.5,'2018-09-18', 2, 0.83425);
  
