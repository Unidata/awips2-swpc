INSERT into awips.swpc_sunspot_quadrant_location (type, description) VALUES (1,'Northeast');
INSERT into awips.swpc_sunspot_quadrant_location (type, description) VALUES (2,'Southeast');
INSERT into awips.swpc_sunspot_quadrant_location (type, description) VALUES (3,'Southwest');
INSERT into awips.swpc_sunspot_quadrant_location (type, description) VALUES (4,'Northwest');

INSERT into awips.swpc_satellite_tracking_status (id, description) VALUES (1, 'Primary');
INSERT into awips.swpc_satellite_tracking_status (id, description) VALUES (2, 'Secondary');
INSERT into awips.swpc_satellite_tracking_status (id, description) VALUES (3, 'Tertiary');

INSERT into awips.swpc_instrument (id, description) VALUES (1,'Magnetometer');
INSERT into awips.swpc_instrument (id, description) VALUES (2, 'Neutron monitor');
INSERT into awips.swpc_instrument (id, description) VALUES (3, 'Optical');
INSERT into awips.swpc_instrument (id, description) VALUES (4, 'Radio');
INSERT into awips.swpc_instrument (id, description) VALUES (5, 'Riometer');
INSERT into awips.swpc_instrument (id, description) VALUES (6, 'X-rays');
INSERT into awips.swpc_instrument (id, description) VALUES (7, 'Protons');
INSERT into awips.swpc_instrument (id, description) VALUES (8, 'Electrons');
INSERT into awips.swpc_instrument (id, description) VALUES (9, 'EIT');
INSERT into awips.swpc_instrument (id, description) VALUES (10, 'LASCO');
INSERT into awips.swpc_instrument (id, description) VALUES (11, 'MDI');

INSERT into awips.swpc_station_type (id, description) VALUES (1, 'Ground');
INSERT into awips.swpc_station_type (id, description) VALUES (2, 'GOES');
INSERT into awips.swpc_station_type (id, description) VALUES (3, 'SOHO');
INSERT into awips.swpc_station_type (id, description) VALUES (4, 'ACE');
INSERT into awips.swpc_station_type (id, description) VALUES (5, 'GOES 14');

INSERT into awips.swpc_station (id,designation,name,type_id) VALUES (2,'BEI','Beijing',1);
INSERT into awips.swpc_station (id,designation,name,type_id) VALUES (3,'BEL','Belsk',1);
INSERT into awips.swpc_station (id,designation,name,type_id) VALUES (5,'BRU','Brussels',1);
INSERT into awips.swpc_station (id,designation,name,type_id) VALUES (6,'CLU','Culgoora',1);
INSERT into awips.swpc_station (id,designation,name,type_id) VALUES (7,'DIX','Dixon',1);
INSERT into awips.swpc_station (id,designation,name,type_id) VALUES (8,'HOL','Holloman',1);
INSERT into awips.swpc_station (id,designation,name,type_id) VALUES (9,'KRE','Krenkel',1);
INSERT into awips.swpc_station (id,designation,name,type_id) VALUES (10,'LEA','Learmonth',1);
INSERT into awips.swpc_station (id,designation,name,type_id) VALUES (11,'MAG','Magadan',1);
INSERT into awips.swpc_station (id,designation,name,type_id) VALUES (13,'MOS','Moscow',1);
INSERT into awips.swpc_station (id,designation,name,type_id) VALUES (14,'MOS','Moscow',1);
INSERT into awips.swpc_station (id,designation,name,type_id) VALUES (15,'MUR','Murmansk',1);
INSERT into awips.swpc_station (id,designation,name,type_id) VALUES (16,'NSK','Novosibirsk',1);
INSERT into awips.swpc_station (id,designation,name,type_id) VALUES (17,'PAL','Palehua',1);
INSERT into awips.swpc_station (id,designation,name,type_id) VALUES (18,'PEN','Penticton',1);
INSERT into awips.swpc_station (id,designation,name,type_id) VALUES (19,'PLA','Planetary',1);
INSERT into awips.swpc_station (id,designation,name,type_id) VALUES (20,'PTU','Tunguska',1);
INSERT into awips.swpc_station (id,designation,name,type_id) VALUES (21,'SAG','Sagamore Hill',1);
INSERT into awips.swpc_station (id,designation,name,type_id) VALUES (22,'SPE','Saint Petersburg',1);
INSERT into awips.swpc_station (id,designation,name,type_id) VALUES (23,'SVI','San Vito',1);
INSERT into awips.swpc_station (id,designation,name,type_id) VALUES (24,'THU','Thule',1);
INSERT into awips.swpc_station (id,designation,name,type_id) VALUES (25,'WIN','Wingst',1);
INSERT into awips.swpc_station (id,designation,name,type_id) VALUES (26,'MEU','Meudon',1);
INSERT into awips.swpc_station (id,designation,name,type_id) VALUES (27,'G10','GOES 10',2);
INSERT into awips.swpc_station (id,designation,name,type_id) VALUES (28,'G11','GOES 11',2);
INSERT into awips.swpc_station (id,designation,name,type_id) VALUES (29,'G12','GOES 12',2);
INSERT into awips.swpc_station (id,designation,name,type_id) VALUES (30,'G13','GOES 13',2);
INSERT into awips.swpc_station (id,designation,name,type_id) VALUES (31,'G14','GOES 14',2);
INSERT into awips.swpc_station (id,designation,name,type_id) VALUES (32,'G15','GOES 15',2);
INSERT into awips.swpc_station (id,designation,name,type_id) VALUES (33,'SOH','SOHO',3);
INSERT into awips.swpc_station (id,designation,name,type_id) VALUES (34,'BOU','Boulder',1);
INSERT into awips.swpc_station (id,designation,name,type_id) VALUES (35,'VKK','Valkarkai',1);
INSERT into awips.swpc_station (id,designation,name,type_id) VALUES (36,'KHA','Khabarovsk',1);
INSERT into awips.swpc_station (id,designation,name,type_id) VALUES (37,'CHA','Chambon-La-Foret',1);
INSERT into awips.swpc_station (id,designation,name,type_id) VALUES (38,'BBG','Barentsburg',1);
INSERT into awips.swpc_station (id,designation,name,type_id) VALUES (39,'AND','Anderma',1);
INSERT into awips.swpc_station (id,designation,name,type_id) VALUES (40,'G06','GOES 6',2);
INSERT into awips.swpc_station (id,designation,name,type_id) VALUES (41,'G07','GOES 7',2);
INSERT into awips.swpc_station (id,designation,name,type_id) VALUES (42,'G08','GOES 8',2);
INSERT into awips.swpc_station (id,designation,name,type_id) VALUES (43,'G05','GOES 5',2);
INSERT into awips.swpc_station (id,designation,name,type_id) VALUES (45,'TOK','Tokyo',1);





INSERT into awips.swpc_station_location (id, station_id, lat, lon, description) VALUES (1,5,0,0,'GOES-14');
INSERT into awips.swpc_station_location (id, station_id,lat,lon, description) VALUES (2,27,0,0,'GOES-10');
INSERT into awips.swpc_station_location (id, station_id,lat,lon, description) VALUES (3,28,0,0,'GOES-11');
INSERT into awips.swpc_station_location (id, station_id,lat,lon, description) VALUES (4,29,0,0,'GOES-12');
INSERT into awips.swpc_station_location (id, station_id,lat,lon, description) VALUES (5,30,0,0,'GOES-13');
INSERT into awips.swpc_station_location (id, station_id,lat,lon, description) VALUES (6,31,0,0,'GOES-14');
INSERT into awips.swpc_station_location (id, station_id,lat,lon, description) VALUES (7,32,0,0,'GOES-15');
INSERT into awips.swpc_station_location (id, station_id,lat,lon, description) VALUES (8,40,0,0,'GOES-06');
INSERT into awips.swpc_station_location (id, station_id,lat,lon, description) VALUES (9,41,0,0,'GOES-07');
INSERT into awips.swpc_station_location (id, station_id,lat,lon, description) VALUES (10,42,0,0,'GOES-08');
INSERT into awips.swpc_station_location (id, station_id,lat,lon, description) VALUES (11,43,0,0,'GOES-05');





INSERT into awips.swpc_station_instrument (id, station_id, instrument_id) VALUES (1, 3, 1);
INSERT into awips.swpc_station_instrument (id, station_id, instrument_id) VALUES (4, 6, 3);
INSERT into awips.swpc_station_instrument (id, station_id, instrument_id) VALUES (5, 6, 4);
INSERT into awips.swpc_station_instrument (id, station_id, instrument_id) VALUES (6, 7, 1);
INSERT into awips.swpc_station_instrument (id, station_id, instrument_id) VALUES (7, 8, 3);
INSERT into awips.swpc_station_instrument (id, station_id, instrument_id) VALUES (8, 9, 1);
INSERT into awips.swpc_station_instrument (id, station_id, instrument_id) VALUES (9, 10, 3);
INSERT into awips.swpc_station_instrument (id, station_id, instrument_id) VALUES (10, 10, 4);
INSERT into awips.swpc_station_instrument (id, station_id, instrument_id) VALUES (11, 11, 1);
INSERT into awips.swpc_station_instrument (id, station_id, instrument_id) VALUES (12, 13, 1);
INSERT into awips.swpc_station_instrument (id, station_id, instrument_id) VALUES (13, 13, 3);
INSERT into awips.swpc_station_instrument (id, station_id, instrument_id) VALUES (14, 13, 4);
INSERT into awips.swpc_station_instrument (id, station_id, instrument_id) VALUES (15, 14, 4);
INSERT into awips.swpc_station_instrument (id, station_id, instrument_id) VALUES (16, 15, 1);
INSERT into awips.swpc_station_instrument (id, station_id, instrument_id) VALUES (17, 16, 1);
INSERT into awips.swpc_station_instrument (id, station_id, instrument_id) VALUES (18, 17, 3);
INSERT into awips.swpc_station_instrument (id, station_id, instrument_id) VALUES (19, 17, 4);
INSERT into awips.swpc_station_instrument (id, station_id, instrument_id) VALUES (20, 18, 4);
INSERT into awips.swpc_station_instrument (id, station_id, instrument_id) VALUES (21, 19, 1);
INSERT into awips.swpc_station_instrument (id, station_id, instrument_id) VALUES (22, 20, 1);
INSERT into awips.swpc_station_instrument (id, station_id, instrument_id) VALUES (23, 21, 4);
INSERT into awips.swpc_station_instrument (id, station_id, instrument_id) VALUES (24, 22, 1);
INSERT into awips.swpc_station_instrument (id, station_id, instrument_id) VALUES (25, 23, 3);
INSERT into awips.swpc_station_instrument (id, station_id, instrument_id) VALUES (26, 23, 4);
INSERT into awips.swpc_station_instrument (id, station_id, instrument_id) VALUES (27, 24, 2);
INSERT into awips.swpc_station_instrument (id, station_id, instrument_id) VALUES (28, 24, 5);
INSERT into awips.swpc_station_instrument (id, station_id, instrument_id) VALUES (29, 26, 3);
INSERT into awips.swpc_station_instrument (id, station_id, instrument_id) VALUES (30, 27, 6);
INSERT into awips.swpc_station_instrument (id, station_id, instrument_id) VALUES (31, 28, 7);
INSERT into awips.swpc_station_instrument (id, station_id, instrument_id) VALUES (32, 28, 8);
INSERT into awips.swpc_station_instrument (id, station_id, instrument_id) VALUES (33, 28, 1);
INSERT into awips.swpc_station_instrument (id, station_id, instrument_id) VALUES (34, 29, 1);
INSERT into awips.swpc_station_instrument (id, station_id, instrument_id) VALUES (35, 31, 6);
INSERT into awips.swpc_station_instrument (id, station_id, instrument_id) VALUES (36, 30, 7);
INSERT into awips.swpc_station_instrument (id, station_id, instrument_id) VALUES (37, 30, 8);
INSERT into awips.swpc_station_instrument (id, station_id, instrument_id) VALUES (38, 30, 1);
INSERT into awips.swpc_station_instrument (id, station_id, instrument_id) VALUES (39, 33, 9);
INSERT into awips.swpc_station_instrument (id, station_id, instrument_id) VALUES (40, 33, 10);
INSERT into awips.swpc_station_instrument (id, station_id, instrument_id) VALUES (41, 33, 11);
INSERT into awips.swpc_station_instrument (id, station_id, instrument_id) VALUES (46, 32, 6);
INSERT into awips.swpc_station_instrument (id, station_id, instrument_id) VALUES (47, 34, 3);
INSERT into awips.swpc_station_instrument (id, station_id, instrument_id) VALUES (48, 30, 6);
INSERT into awips.swpc_station_instrument (id, station_id, instrument_id) VALUES (49, 31, 1);
INSERT into awips.swpc_station_instrument (id, station_id, instrument_id) VALUES (50, 31, 7);
INSERT into awips.swpc_station_instrument (id, station_id, instrument_id) VALUES (51, 31, 8);
INSERT into awips.swpc_station_instrument (id, station_id, instrument_id) VALUES (52, 32, 1);
INSERT into awips.swpc_station_instrument (id, station_id, instrument_id) VALUES (53, 32, 7);
INSERT into awips.swpc_station_instrument (id, station_id, instrument_id) VALUES (54, 32, 8);
INSERT into awips.swpc_station_instrument (id, station_id, instrument_id) VALUES (55, 40, 8);
INSERT into awips.swpc_station_instrument (id, station_id, instrument_id) VALUES (56, 41, 8);
INSERT into awips.swpc_station_instrument (id, station_id, instrument_id) VALUES (57, 42, 8);
INSERT into awips.swpc_station_instrument (id, station_id, instrument_id) VALUES (58, 27, 8);
INSERT into awips.swpc_station_instrument (id, station_id, instrument_id) VALUES (59, 43, 8);
INSERT into awips.swpc_station_instrument (id, station_id, instrument_id) VALUES (60, 29, 8);
INSERT into awips.swpc_station_instrument (id, station_id, instrument_id) VALUES (61, 40, 7);
INSERT into awips.swpc_station_instrument (id, station_id, instrument_id) VALUES (62, 41, 7);
INSERT into awips.swpc_station_instrument (id, station_id, instrument_id) VALUES (63, 42, 7);
INSERT into awips.swpc_station_instrument (id, station_id, instrument_id) VALUES (64, 27, 7);
INSERT into awips.swpc_station_instrument (id, station_id, instrument_id) VALUES (65, 43, 7);
INSERT into awips.swpc_station_instrument (id, station_id, instrument_id) VALUES (66, 29, 7);
INSERT into awips.swpc_station_instrument (id, station_id, instrument_id) VALUES (67, 40, 6);
INSERT into awips.swpc_station_instrument (id, station_id, instrument_id) VALUES (68, 41, 6);
INSERT into awips.swpc_station_instrument (id, station_id, instrument_id) VALUES (69, 42, 6);
INSERT into awips.swpc_station_instrument (id, station_id, instrument_id) VALUES (70, 29, 6);
INSERT into awips.swpc_station_instrument (id, station_id, instrument_id) VALUES (71, 28, 6);
INSERT into awips.swpc_station_instrument (id, station_id, instrument_id) VALUES (72, 43, 6);
INSERT into awips.swpc_station_instrument (id, station_id, instrument_id) VALUES (73, 5, 6);





INSERT INTO awips.swpc_tracking_status 
	(id, 
	stationinstrument_id, 
	satellitetrackingstatus_id, 
	description, 
	begin_dttm, 
	end_dttm) VALUES (1, 30, 1, null, '2007-01-01 00:00:00.000', '2009-12-01 00:00:00.000');
INSERT INTO awips.swpc_tracking_status (id, stationinstrument_id, satellitetrackingstatus_id, description, begin_dttm, end_dttm) VALUES (3, 31, 2, null, '2010-04-14 15:30:01.000', '2011-02-28 18:00:00.000');
INSERT INTO awips.swpc_tracking_status (id, stationinstrument_id, satellitetrackingstatus_id, description, begin_dttm, end_dttm) VALUES (5, 32, 2, null, '2010-04-14 15:30:01.000', '2011-02-28 18:00:00.000');
INSERT INTO awips.swpc_tracking_status (id, stationinstrument_id, satellitetrackingstatus_id, description, begin_dttm, end_dttm) VALUES (6, 33, 2, null, '2007-01-01 00:00:00.000', '2011-02-28 18:00:00.000');
INSERT INTO awips.swpc_tracking_status (id, stationinstrument_id, satellitetrackingstatus_id, description, begin_dttm, end_dttm) VALUES (7, 34, 1, null, '2007-01-01 00:00:00.000', '2010-04-14 15:30:00.000');
INSERT INTO awips.swpc_tracking_status (id, stationinstrument_id, satellitetrackingstatus_id, description, begin_dttm, end_dttm) VALUES (8, 35, 1, null, '2009-12-01 00:00:01.000', '2010-10-28 00:00:00.000');
INSERT INTO awips.swpc_tracking_status (id, stationinstrument_id, satellitetrackingstatus_id, description, begin_dttm, end_dttm) VALUES (9, 36, 1, null, '2010-04-14 15:30:01.000', '2013-05-22 15:10:00.000');
INSERT INTO awips.swpc_tracking_status (id, stationinstrument_id, satellitetrackingstatus_id, description, begin_dttm, end_dttm) VALUES (10, 37, 1, null, '2010-04-14 15:30:01.000', '2013-05-22 15:10:00.000');
INSERT INTO awips.swpc_tracking_status (id, stationinstrument_id, satellitetrackingstatus_id, description, begin_dttm, end_dttm) VALUES (11, 38, 1, null, '2010-04-14 15:30:01.000', '2013-05-22 15:10:00.000');
INSERT INTO awips.swpc_tracking_status (id, stationinstrument_id, satellitetrackingstatus_id, description, begin_dttm, end_dttm) VALUES (12, 46, 2, null, '2010-09-01 00:00:00.000', '2010-10-28 00:00:00.000');
INSERT INTO awips.swpc_tracking_status (id, stationinstrument_id, satellitetrackingstatus_id, description, begin_dttm, end_dttm) VALUES (16, 46, 1, null, '2010-10-28 00:00:01.000', '2012-10-23 16:00:00.000');
INSERT INTO awips.swpc_tracking_status (id, stationinstrument_id, satellitetrackingstatus_id, description, begin_dttm, end_dttm) VALUES (21, 52, 2, null, '2011-02-28 18:00:01.000', '2012-10-23 22:00:00.000');
INSERT INTO awips.swpc_tracking_status (id, stationinstrument_id, satellitetrackingstatus_id, description, begin_dttm, end_dttm) VALUES (22, 53, 2, null, '2011-02-28 18:00:01.000', '2012-10-23 22:00:00.000');
INSERT INTO awips.swpc_tracking_status (id, stationinstrument_id, satellitetrackingstatus_id, description, begin_dttm, end_dttm) VALUES (23, 54, 2, null, '2011-02-28 18:00:01.000', '2012-10-23 22:00:00.000');
INSERT INTO awips.swpc_tracking_status (id, stationinstrument_id, satellitetrackingstatus_id, description, begin_dttm, end_dttm) VALUES (24, 55, 1, null, '1986-01-01 00:00:00.000', '1988-01-26 00:00:00.000');
INSERT INTO awips.swpc_tracking_status (id, stationinstrument_id, satellitetrackingstatus_id, description, begin_dttm, end_dttm) VALUES (25, 56, 1, null, '1988-01-26 00:00:01.000', '1995-01-05 00:00:00.000');
INSERT INTO awips.swpc_tracking_status (id, stationinstrument_id, satellitetrackingstatus_id, description, begin_dttm, end_dttm) VALUES (26, 57, 1, null, '1995-01-05 00:00:01.000', '2003-04-08 15:00:00.000');
INSERT INTO awips.swpc_tracking_status (id, stationinstrument_id, satellitetrackingstatus_id, description, begin_dttm, end_dttm) VALUES (27, 58, 1, null, '2003-04-08 15:00:01.000', '2003-05-15 15:00:00.000');
INSERT INTO awips.swpc_tracking_status (id, stationinstrument_id, satellitetrackingstatus_id, description, begin_dttm, end_dttm) VALUES (28, 57, 1, null, '2003-05-15 15:00:01.000', '2003-06-18 17:00:00.000');
INSERT INTO awips.swpc_tracking_status (id, stationinstrument_id, satellitetrackingstatus_id, description, begin_dttm, end_dttm) VALUES (29, 32, 1, null, '2003-06-18 17:00:01.000', '2010-04-14 15:30:00.000');
INSERT INTO awips.swpc_tracking_status (id, stationinstrument_id, satellitetrackingstatus_id, description, begin_dttm, end_dttm) VALUES (30, 59, 2, null, '1986-01-01 00:00:00.000', '1988-01-26 00:00:00.000');
INSERT INTO awips.swpc_tracking_status (id, stationinstrument_id, satellitetrackingstatus_id, description, begin_dttm, end_dttm) VALUES (31, 55, 2, null, '1988-01-26 00:00:01.000', '1994-12-11 00:00:00.000');
INSERT INTO awips.swpc_tracking_status (id, stationinstrument_id, satellitetrackingstatus_id, description, begin_dttm, end_dttm) VALUES (32, 57, 2, null, '1994-12-11 00:00:01.000', '1995-01-05 00:00:00.000');
INSERT INTO awips.swpc_tracking_status (id, stationinstrument_id, satellitetrackingstatus_id, description, begin_dttm, end_dttm) VALUES (33, 56, 2, null, '1995-03-01 00:00:01.000', '1998-07-27 00:00:00.000');
INSERT INTO awips.swpc_tracking_status (id, stationinstrument_id, satellitetrackingstatus_id, description, begin_dttm, end_dttm) VALUES (34, 58, 2, null, '1998-07-27 00:00:01.000', '2003-04-08 15:00:00.000');
INSERT INTO awips.swpc_tracking_status (id, stationinstrument_id, satellitetrackingstatus_id, description, begin_dttm, end_dttm) VALUES (35, 60, 2, null, '2003-04-08 15:00:01.000', '2003-04-26 00:00:00.000');
INSERT INTO awips.swpc_tracking_status (id, stationinstrument_id, satellitetrackingstatus_id, description, begin_dttm, end_dttm) VALUES (36, 58, 2, null, '2003-05-15 15:00:01.000', '2003-06-19 00:00:00.000');
INSERT INTO awips.swpc_tracking_status (id, stationinstrument_id, satellitetrackingstatus_id, description, begin_dttm, end_dttm) VALUES (37, 58, 2, null, '2006-06-22 14:00:01.000', '2006-06-23 00:00:00.000');
INSERT INTO awips.swpc_tracking_status (id, stationinstrument_id, satellitetrackingstatus_id, description, begin_dttm, end_dttm) VALUES (38, 58, 2, null, '2007-12-05 00:00:01.000', '2007-12-18 00:00:00.000');
INSERT INTO awips.swpc_tracking_status (id, stationinstrument_id, satellitetrackingstatus_id, description, begin_dttm, end_dttm) VALUES (39, 58, 2, null, '2008-11-28 00:00:01.000', '2009-12-01 00:00:00.000');
INSERT INTO awips.swpc_tracking_status (id, stationinstrument_id, satellitetrackingstatus_id, description, begin_dttm, end_dttm) VALUES (40, 61, 1, null, '1986-01-01 00:00:00.000', '1988-01-26 00:00:00.000');
INSERT INTO awips.swpc_tracking_status (id, stationinstrument_id, satellitetrackingstatus_id, description, begin_dttm, end_dttm) VALUES (41, 62, 1, null, '1988-01-26 00:00:01.000', '1995-01-05 00:00:00.000');
INSERT INTO awips.swpc_tracking_status (id, stationinstrument_id, satellitetrackingstatus_id, description, begin_dttm, end_dttm) VALUES (42, 63, 1, null, '1995-01-05 00:00:01.000', '2003-04-08 15:00:00.000');
INSERT INTO awips.swpc_tracking_status (id, stationinstrument_id, satellitetrackingstatus_id, description, begin_dttm, end_dttm) VALUES (43, 64, 1, null, '2003-04-08 15:00:01.000', '2003-05-15 15:00:00.000');
INSERT INTO awips.swpc_tracking_status (id, stationinstrument_id, satellitetrackingstatus_id, description, begin_dttm, end_dttm) VALUES (44, 63, 1, null, '2003-05-15 15:00:01.000', '2003-06-18 17:00:00.000');
INSERT INTO awips.swpc_tracking_status (id, stationinstrument_id, satellitetrackingstatus_id, description, begin_dttm, end_dttm) VALUES (45, 31, 1, null, '2003-06-18 17:00:01.000', '2010-04-14 15:30:00.000');
INSERT INTO awips.swpc_tracking_status (id, stationinstrument_id, satellitetrackingstatus_id, description, begin_dttm, end_dttm) VALUES (46, 65, 2, null, '1986-01-01 00:00:00.000', '1988-01-26 00:00:00.000');
INSERT INTO awips.swpc_tracking_status (id, stationinstrument_id, satellitetrackingstatus_id, description, begin_dttm, end_dttm) VALUES (47, 61, 2, null, '1988-01-26 00:00:01.000', '1994-12-11 00:00:00.000');
INSERT INTO awips.swpc_tracking_status (id, stationinstrument_id, satellitetrackingstatus_id, description, begin_dttm, end_dttm) VALUES (48, 63, 2, null, '1994-12-11 00:00:01.000', '1995-01-05 00:00:00.000');
INSERT INTO awips.swpc_tracking_status (id, stationinstrument_id, satellitetrackingstatus_id, description, begin_dttm, end_dttm) VALUES (49, 62, 2, null, '1995-03-01 00:00:01.000', '1998-07-27 00:00:00.000');
INSERT INTO awips.swpc_tracking_status (id, stationinstrument_id, satellitetrackingstatus_id, description, begin_dttm, end_dttm) VALUES (50, 64, 2, null, '1998-07-27 00:00:01.000', '2003-04-08 15:00:00.000');
INSERT INTO awips.swpc_tracking_status (id, stationinstrument_id, satellitetrackingstatus_id, description, begin_dttm, end_dttm) VALUES (51, 66, 2, null, '2003-04-08 15:00:01.000', '2003-04-26 00:00:00.000');
INSERT INTO awips.swpc_tracking_status (id, stationinstrument_id, satellitetrackingstatus_id, description, begin_dttm, end_dttm) VALUES (52, 64, 2, null, '2003-05-15 15:00:01.000', '2003-06-19 00:00:00.000');
INSERT INTO awips.swpc_tracking_status (id, stationinstrument_id, satellitetrackingstatus_id, description, begin_dttm, end_dttm) VALUES (53, 64, 2, null, '2006-06-22 14:00:01.000', '2006-06-23 00:00:00.000');
INSERT INTO awips.swpc_tracking_status (id, stationinstrument_id, satellitetrackingstatus_id, description, begin_dttm, end_dttm) VALUES (54, 64, 2, null, '2007-12-05 00:00:01.000', '2007-12-18 00:00:00.000');
INSERT INTO awips.swpc_tracking_status (id, stationinstrument_id, satellitetrackingstatus_id, description, begin_dttm, end_dttm) VALUES (55, 64, 2, null, '2008-11-28 00:00:01.000', '2009-12-01 00:00:00.000');
INSERT INTO awips.swpc_tracking_status (id, stationinstrument_id, satellitetrackingstatus_id, description, begin_dttm, end_dttm) VALUES (56, 67, 1, null, '1986-01-01 00:00:00.000', '1988-01-26 00:00:00.000');
INSERT INTO awips.swpc_tracking_status (id, stationinstrument_id, satellitetrackingstatus_id, description, begin_dttm, end_dttm) VALUES (57, 68, 1, null, '1988-01-26 00:00:01.000', '1995-03-01 00:00:00.000');
INSERT INTO awips.swpc_tracking_status (id, stationinstrument_id, satellitetrackingstatus_id, description, begin_dttm, end_dttm) VALUES (58, 69, 1, null, '1995-03-01 00:00:01.000', '2003-04-08 15:00:00.000');
INSERT INTO awips.swpc_tracking_status (id, stationinstrument_id, satellitetrackingstatus_id, description, begin_dttm, end_dttm) VALUES (59, 30, 1, null, '2003-04-08 15:00:01.000', '2003-05-15 15:00:00.000');
INSERT INTO awips.swpc_tracking_status (id, stationinstrument_id, satellitetrackingstatus_id, description, begin_dttm, end_dttm) VALUES (60, 70, 1, null, '2003-05-15 15:00:01.000', '2007-04-12 00:00:00.000');
INSERT INTO awips.swpc_tracking_status (id, stationinstrument_id, satellitetrackingstatus_id, description, begin_dttm, end_dttm) VALUES (61, 71, 1, null, '2007-04-12 00:00:01.000', '2008-02-10 16:30:00.000');
INSERT INTO awips.swpc_tracking_status (id, stationinstrument_id, satellitetrackingstatus_id, description, begin_dttm, end_dttm) VALUES (62, 30, 1, null, '2008-02-10 16:30:01.000', '2009-12-01 00:00:00.000');
INSERT INTO awips.swpc_tracking_status (id, stationinstrument_id, satellitetrackingstatus_id, description, begin_dttm, end_dttm) VALUES (63, 72, 2, null, '1986-01-01 00:00:00.000', '1988-01-26 00:00:00.000');
INSERT INTO awips.swpc_tracking_status (id, stationinstrument_id, satellitetrackingstatus_id, description, begin_dttm, end_dttm) VALUES (64, 67, 2, null, '1988-01-26 00:00:01.000', '1994-12-11 00:00:00.000');
INSERT INTO awips.swpc_tracking_status (id, stationinstrument_id, satellitetrackingstatus_id, description, begin_dttm, end_dttm) VALUES (65, 69, 2, null, '1994-12-11 00:00:01.000', '1995-03-01 00:00:00.000');
INSERT INTO awips.swpc_tracking_status (id, stationinstrument_id, satellitetrackingstatus_id, description, begin_dttm, end_dttm) VALUES (66, 68, 2, null, '1995-03-01 00:00:01.000', '1998-07-27 00:00:00.000');
INSERT INTO awips.swpc_tracking_status (id, stationinstrument_id, satellitetrackingstatus_id, description, begin_dttm, end_dttm) VALUES (67, 30, 2, null, '1998-07-27 00:00:01.000', '2003-04-08 15:00:00.000');
INSERT INTO awips.swpc_tracking_status (id, stationinstrument_id, satellitetrackingstatus_id, description, begin_dttm, end_dttm) VALUES (68, 70, 2, null, '2003-04-08 15:00:01.000', '2003-05-15 15:00:00.000');
INSERT INTO awips.swpc_tracking_status (id, stationinstrument_id, satellitetrackingstatus_id, description, begin_dttm, end_dttm) VALUES (69, 30, 2, null, '2003-05-15 15:00:01.000', '2006-06-22 14:00:00.000');
INSERT INTO awips.swpc_tracking_status (id, stationinstrument_id, satellitetrackingstatus_id, description, begin_dttm, end_dttm) VALUES (70, 71, 2, null, '2006-06-28 00:00:01.000', '2007-04-12 00:00:00.000');
INSERT INTO awips.swpc_tracking_status (id, stationinstrument_id, satellitetrackingstatus_id, description, begin_dttm, end_dttm) VALUES (71, 30, 2, null, '2007-04-12 00:00:01.000', '2007-11-21 00:00:00.000');
INSERT INTO awips.swpc_tracking_status (id, stationinstrument_id, satellitetrackingstatus_id, description, begin_dttm, end_dttm) VALUES (72, 30, 2, null, '2007-12-05 00:00:01.000', '2007-12-18 00:00:00.000');
INSERT INTO awips.swpc_tracking_status (id, stationinstrument_id, satellitetrackingstatus_id, description, begin_dttm, end_dttm) VALUES (73, 73, 2, null, '2011-09-01 00:00:00.000', NULL);
INSERT INTO awips.swpc_tracking_status (id, stationinstrument_id, satellitetrackingstatus_id, description, begin_dttm, end_dttm) VALUES (74, 35, 2, null, '2011-09-01 00:00:00.000', '2012-10-23 16:00:00.000');
INSERT INTO awips.swpc_tracking_status (id, stationinstrument_id, satellitetrackingstatus_id, description, begin_dttm, end_dttm) VALUES (75, 35, 1, null, '2012-10-23 16:00:00.000', '2012-11-19 16:31:00.000');
INSERT INTO awips.swpc_tracking_status (id, stationinstrument_id, satellitetrackingstatus_id, description, begin_dttm, end_dttm) VALUES (76, 46, 2, null, '2012-10-23 16:00:00.000', '2012-11-19 16:31:00.000');
INSERT INTO awips.swpc_tracking_status (id, stationinstrument_id, satellitetrackingstatus_id, description, begin_dttm, end_dttm) VALUES (77, 50, 2, null, '2012-10-23 22:00:00.000', '2012-11-19 16:31:00.000');
INSERT INTO awips.swpc_tracking_status (id, stationinstrument_id, satellitetrackingstatus_id, description, begin_dttm, end_dttm) VALUES (78, 51, 2, null, '2012-10-23 22:00:00.000', '2012-11-19 16:31:00.000');
INSERT INTO awips.swpc_tracking_status (id, stationinstrument_id, satellitetrackingstatus_id, description, begin_dttm, end_dttm) VALUES (79, 49, 2, null, '2012-10-23 22:00:00.000', '2012-11-19 16:31:00.000');
INSERT INTO awips.swpc_tracking_status (id, stationinstrument_id, satellitetrackingstatus_id, description, begin_dttm, end_dttm) VALUES (80, 35, 2, null, '2012-11-19 16:31:01.000', '2015-01-26 16:00:00.000');
INSERT INTO awips.swpc_tracking_status (id, stationinstrument_id, satellitetrackingstatus_id, description, begin_dttm, end_dttm) VALUES (81, 46, 1, null, '2012-11-19 16:31:01.000', '2015-05-21 18:00:00.000');
INSERT INTO awips.swpc_tracking_status (id, stationinstrument_id, satellitetrackingstatus_id, description, begin_dttm, end_dttm) VALUES (82, 50, 2, null, '2012-10-23 16:00:00.000', '2012-11-19 16:31:00.000');
INSERT INTO awips.swpc_tracking_status (id, stationinstrument_id, satellitetrackingstatus_id, description, begin_dttm, end_dttm) VALUES (83, 53, 2, null, '2012-11-19 16:31:01.000', '2013-05-22 15:10:00.000');
INSERT INTO awips.swpc_tracking_status (id, stationinstrument_id, satellitetrackingstatus_id, description, begin_dttm, end_dttm) VALUES (84, 54, 2, null, '2012-11-19 16:31:01.000', '2013-05-22 15:10:00.000');
INSERT INTO awips.swpc_tracking_status (id, stationinstrument_id, satellitetrackingstatus_id, description, begin_dttm, end_dttm) VALUES (85, 52, 2, null, '2012-11-19 16:31:01.000', '2013-05-22 15:10:00.000');
INSERT INTO awips.swpc_tracking_status (id, stationinstrument_id, satellitetrackingstatus_id, description, begin_dttm, end_dttm) VALUES (86, 53, 1, null, '2013-05-22 15:10:01.000', '2013-06-05 21:00:00.000');
INSERT INTO awips.swpc_tracking_status (id, stationinstrument_id, satellitetrackingstatus_id, description, begin_dttm, end_dttm) VALUES (87, 36, 2, null, '2013-05-22 15:10:01.000', '2013-06-05 21:00:00.000');
INSERT INTO awips.swpc_tracking_status (id, stationinstrument_id, satellitetrackingstatus_id, description, begin_dttm, end_dttm) VALUES (88, 54, 1, null, '2013-05-22 15:10:01.000', '2013-06-05 21:00:00.000');
INSERT INTO awips.swpc_tracking_status (id, stationinstrument_id, satellitetrackingstatus_id, description, begin_dttm, end_dttm) VALUES (89, 37, 2, null, '2013-05-22 15:10:01.000', '2013-06-05 21:00:00.000');
INSERT INTO awips.swpc_tracking_status (id, stationinstrument_id, satellitetrackingstatus_id, description, begin_dttm, end_dttm) VALUES (90, 52, 1, null, '2013-05-22 15:10:01.000', '2013-06-05 21:00:00.000');
INSERT INTO awips.swpc_tracking_status (id, stationinstrument_id, satellitetrackingstatus_id, description, begin_dttm, end_dttm) VALUES (91, 38, 2, null, '2013-05-22 15:10:01.000', '2013-06-05 21:00:00.000');
INSERT INTO awips.swpc_tracking_status (id, stationinstrument_id, satellitetrackingstatus_id, description, begin_dttm, end_dttm) VALUES (94, 36, 1, null, '2013-06-05 21:00:01.000', NULL);
INSERT INTO awips.swpc_tracking_status (id, stationinstrument_id, satellitetrackingstatus_id, description, begin_dttm, end_dttm) VALUES (95, 53, 2, null, '2013-06-05 21:00:01.000', NULL);
INSERT INTO awips.swpc_tracking_status (id, stationinstrument_id, satellitetrackingstatus_id, description, begin_dttm, end_dttm) VALUES (96, 53, 1, null, '2013-06-05 21:00:01.000', '2013-06-05 21:00:00.000');
INSERT INTO awips.swpc_tracking_status (id, stationinstrument_id, satellitetrackingstatus_id, description, begin_dttm, end_dttm) VALUES (97, 36, 2, null, '2013-06-05 21:00:01.000', '2013-06-05 21:00:00.000');
INSERT INTO awips.swpc_tracking_status (id, stationinstrument_id, satellitetrackingstatus_id, description, begin_dttm, end_dttm) VALUES (98, 37, 1, null, '2013-06-05 21:00:01.000', NULL);
INSERT INTO awips.swpc_tracking_status (id, stationinstrument_id, satellitetrackingstatus_id, description, begin_dttm, end_dttm) VALUES (99, 54, 2, null, '2013-06-05 21:00:01.000', NULL);
INSERT INTO awips.swpc_tracking_status (id, stationinstrument_id, satellitetrackingstatus_id, description, begin_dttm, end_dttm) VALUES (100, 38, 1, null, '2013-06-05 21:00:01.000', NULL);
INSERT INTO awips.swpc_tracking_status (id, stationinstrument_id, satellitetrackingstatus_id, description, begin_dttm, end_dttm) VALUES (101, 52, 2, null, '2013-06-05 21:00:01.000', NULL);
INSERT INTO awips.swpc_tracking_status (id, stationinstrument_id, satellitetrackingstatus_id, description, begin_dttm, end_dttm) VALUES (102, 48, 2, null, '2012-01-26 16:01:00.000', NULL);
INSERT INTO awips.swpc_tracking_status (id, stationinstrument_id, satellitetrackingstatus_id, description, begin_dttm, end_dttm) VALUES (103, 35, 1, null, '2015-05-21 18:00:01.000', '2015-06-09 16:25:00.000');
INSERT INTO awips.swpc_tracking_status (id, stationinstrument_id, satellitetrackingstatus_id, description, begin_dttm, end_dttm) VALUES (104, 46, 1, null, '2015-06-09 16:25:01.000', NULL);


insert into swpc_housekeeping values (1, 'Events', 'Tue Jan 19 19:38:55 GMT 2016', 'Keep track of bin numbers used', null, 0, 1000, 0, 0, 0);

