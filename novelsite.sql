create table manager (
MName char(20) not null,
ManageID int(8) primary key,
MPWd int(8) not null
)ENGINE=InnoDB  DEFAULT CHARSET=utf8;
create table book(
BID int(8) not null primary key,
Bname char(20) not null,
Auther char(20) ,
picture varchar(225),
votes int(11) not null DEFAULT 0,
hits int(11) not null DEFAULT 0,
state tinyint(10) not null DEFAULT 0,
wordcount int(11) not null DEFAULT 0,
content text ,
createtime int(10) not null,
updatetime int(10) not null
)ENGINE=InnoDB  DEFAULT CHARSET=utf8;
create table chapter(
chapterid int(8) not null primary key,
chaptername char(20) not null,
bid int(8) not null,
part longtext,
state tinyint(10) not null,
foreign key (bid) REFERENCES book(bid)
)ENGINE=InnoDB  DEFAULT CHARSET=utf8;
create table users(
uname char(20) not null,
uid int(8) not null primary key,
usex char(20) ,
umoney char(11) not null,
UContent Text ,
UBirth Char(20) not null,
Upwd Char(32) not null,
HeadPicture Varchar(255),
Recommend Int(11) not null DEFAULT 0,
EditorStatus Tinyint(2) not null DEFAULT 0
)ENGINE=InnoDB  DEFAULT CHARSET=utf8;
create table PURCHASE(
ID Int(8) not null primary key,
RechargeMoney Int(11) not null DEFAULT 0,
Token Int(11)  not null DEFAULT 0,
PurchaseTime Int(10) not null,
UID Int(8) not null,
foreign key (uid) REFERENCES users(uid)
)ENGINE=InnoDB  DEFAULT CHARSET=utf8;
create table BOOKSHELF(
id Int(8) not null primary key,
BookShelfstatus Tinyint(2) not null DEFAULT 0,
BID Int(8) not null,
ChapterID Int(8) not null,
UID Int(8) not null,
foreign key (uid) REFERENCES users(uid),
foreign key (bid) REFERENCES book(bid),
foreign key (ChapterID) REFERENCES Chapter(ChapterID)
)ENGINE=InnoDB  DEFAULT CHARSET=utf8;
create table classify(
id int(8) not null primary key,
ClassifyName Char(60) not null,
BID Int(8) not null,
foreign key (bid) REFERENCES book(bid)
)ENGINE=InnoDB  DEFAULT CHARSET=utf8;
