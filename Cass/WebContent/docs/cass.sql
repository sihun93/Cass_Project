/* 포인트 상품 게시판 */
CREATE TABLE point_board (
	pboard_num VARCHAR2(10) NOT NULL, /* 게시글넘버 */
	mcategory_num VARCHAR2(5), /* 메인카테고리 번호 */
	pboard_title VARCHAR2(100), /* 제목 */
	pboard_img VARCHAR2(255), /* 이미지 */
	pboard_content CLOB, /* 내용 */
	pboard_price NUMBER /* 마일리지가격 */
);

CREATE UNIQUE INDEX PK_point_board
	ON point_board (
		pboard_num ASC
	);

ALTER TABLE point_board
	ADD
		CONSTRAINT PK_point_board
		PRIMARY KEY (
			pboard_num
		);

create sequence pboard_num_seq
start with 1
increment by 1;
insert into point_board values('p' || pboard_num_seq.nextval, 'mc8', '강아지안경', '강아지안경.jpg', '귀여운 강아지 안경', 4000);
insert into point_board values('p' || pboard_num_seq.nextval, 'mc5', '고양이사료', '고양이사료.jpg', '전연령층이 먹을 수 있는 고양이 사료', 15000);

============================================================================================

/*Q & A 게시판*/

CREATE TABLE A_board (
	Qboard_num VARCHAR2(10) NOT NULL, /* 게시글 넘버 */
	Aboard_writer VARCHAR2(30), /* 작성자 */
	Aboard_content CLOB, /* 내용 */
	Aboard_date DATE /* 작성일 */
);




insert into A_board values('qb1', 'Admin', '문의사항에 대한 답변입니다.', '2020-03-19');
insert into A_board values('qb2', 'Admin', '문의사항2에 대한 답변입니다.', '2021-02-23');
insert into A_board values('qb3', 'Admin', '배송문의에 대한 답변입니다.', '2018-07-04');
insert into A_board values('qb4', 'Admin', '배송문의2에 대한 답변입니다.', '2021-05-03');


CREATE TABLE Q_board (
	Qboard_num VARCHAR2(10) NOT NULL, /* 게시글 넘버 */
	Qboard_title VARCHAR2(100), /* 제목 */
	Qboard_content CLOB, /* 내용 */
	Qboard_date DATE default sysdate, /* 작성일 */
	Qboard_img VARCHAR2(255), /* 이미지 */
	member_id VARCHAR2(30) /* 아이디 */
);

ALTER TABLE Q_board
	ADD
		CONSTRAINT PK_Q_board
		PRIMARY KEY (
			Qboard_num
		);
create sequence q_num_seq
	start with 1
	increment by 1
	maxvalue 99999
	nocycle;
    
insert into Q_board values('qb'||q_num_seq.nextval, '문의사항', '문의사항', '2020-03-18', 'qboard_img1.jpg', 'room93');
insert into Q_board values('qb'||q_num_seq.nextval, '문의사항2', '문의사항2', '2021-02-22', null, 'okoo');
insert into Q_board values('qb'||q_num_seq.nextval, '배송문의', '배송문의', '2018-07-02', null, 'money99');
insert into Q_board values('qb'||q_num_seq.nextval, '배송문의2', '배송문의2', '2021-05-01', null, 'Rhdemr2');

============================================================================================
drop table review;
drop table mainboard;
DROP TABLE sub_category;
DROP TABLE main_category;

/* 메인 카테고리 */
CREATE TABLE main_category (
	mcategory_num VARCHAR2(5) NOT NULL, /* 메인카테고리 번호 */
	mcategory_name VARCHAR2(30) /* 카테고리 이름 */
);

ALTER TABLE main_category
	ADD
		CONSTRAINT PK_main_category
		PRIMARY KEY (
			mcategory_num
		);			
INSERT INTO main_category VALUES ('mc1',	'애완용품');
INSERT INTO main_category VALUES ('mc2',	'관리');
INSERT INTO main_category VALUES ('mc3',	'교육');
INSERT INTO main_category VALUES ('mc4',	'사료');
INSERT INTO main_category VALUES ('mc5',	'사료');
INSERT INTO main_category VALUES ('mc6',	'장난감');
INSERT INTO main_category VALUES ('mc7',	'악세서리');
INSERT INTO main_category VALUES ('mc8',	'미용상품');
===========================================================================================

/* 서브 카테고리 */
CREATE TABLE sub_category (
	scategory_num VARCHAR2(5) NOT NULL, /* 서브카테고리번호 */
	mcategory_num VARCHAR2(5), /* 메인카테고리 번호 */
	scategory_name VARCHAR2(30) /* 서브카테고리이름 */
);
ALTER TABLE sub_category
	ADD
		CONSTRAINT PK_sub_category
		PRIMARY KEY (
			scategory_num
		);

INSERT INTO sub_category VALUES('sc1','mc1','장난감');
INSERT INTO sub_category VALUES('sc2','mc1','집');
INSERT INTO sub_category VALUES('sc3','mc1','악세서리');
INSERT INTO sub_category VALUES('sc4','mc1','미용 용품');
INSERT INTO sub_category VALUES('sc5','mc2','돌봄 서비스');
INSERT INTO sub_category VALUES('sc6','mc2','병원' );
INSERT INTO sub_category VALUES('sc7','mc2','미용' );
INSERT INTO sub_category VALUES('sc8','mc2','장례식장');
INSERT INTO sub_category VALUES('sc9','mc3','애견학교');
INSERT INTO sub_category VALUES('sc10','mc4','간식' );
INSERT INTO sub_category VALUES('sc11','mc4','주식' );





===========================================================================================
/* 리뷰 */
CREATE TABLE review (
	review_num NUMBER(5) NOT NULL, /* 리뷰번호 */
	mboard_num VARCHAR2(10), /* 게시글넘버 */
	member_id VARCHAR2(30), /* 아이디 */
	review_score NUMBER(2), /* 별점 */
	review_content CLOB, /* 내용 */
	review_img VARCHAR2(255), /* 이미지 */
	review_date DATE /* 작성일 */
);
ALTER TABLE review
	ADD
		CONSTRAINT PK_review
		PRIMARY KEY (
			review_num
		);
drop sequence seq_review;
create sequence seq_review
start with 1
increment by 1;
===========================================================================================
/* 매인서비스 글작성 테이블 */
CREATE TABLE mainboard (
	mboard_num VARCHAR2(10) NOT NULL, /* 게시글넘버 */
	business_id VARCHAR2(30), /* 아이디 */
	mcategory_num VARCHAR2(5), /* 메인카테고리 번호 */
	scategory_num VARCHAR2(5), /* 서브카테고리번호 */
	mboard_title VARCHAR2(100), /* 제목 */
	mboard_score NUMBER(2), /* 평점 */
	mboard_img VARCHAR2(255), /* 회사 이미지 */
	mboard_info CLOB, /* 회사정보 */
	mboard_content CLOB /* 메인서비스 내용 */
);

ALTER TABLE mainboard
	ADD
		CONSTRAINT PK_mainboard
		PRIMARY KEY (
			mboard_num
		);
		
drop sequence seq_mainboard;
create sequence seq_mainboard
start with 1
increment by 1;
==========================================================================================
/* 포인트 상품 구매내역 */
CREATE TABLE point_buy (
    buy_num VARCHAR2(10) NOT NULL, /* 내역 게시글 넘버 */
	pboard_num VARCHAR2(10) NOT NULL, /* 포인트 게시글넘버 */
    member_id VARCHAR2(30) NOT NULL, /* 아이디 */
	pboard_title VARCHAR2(100), /* 제목 */
	pboard_img VARCHAR2(255), /* 이미지 */
    pboard_count NUMBER, /* 구매갯수 */
	pboard_price NUMBER, /* 마일리지가격 */
    buy_date VARCHAR2(255) /* 구매날짜 */
);

create sequence buy_num_seq
start with 1
increment by 1;


insert into point_buy values('b' || buy_num_seq.nextval, 'p1' , 'snssnskssk', '고양이 츄르' , '츄르.jpg' ,1, 2000, to_char(sysdate, 'yyyy-mm-dd'));
insert into point_buy values('b' || buy_num_seq.nextval, 'p2' , 'engage', '강아지 장난감' , '강아지 장난감.jpg' ,1, 5000, to_char(sysdate, 'yyyy-mm-dd'));

===========================================================================================
drop table business;
/*사업자 회원 데이터*/
CREATE TABLE business (
	business_id VARCHAR2(30) NOT NULL, /* 아이디 */
	business_pw VARCHAR2(30), /* 비밀번호 */
	business_num VARCHAR2(12), /* 사업자번호 */
	business_title VARCHAR2(100), /* 상호명 */
	business_addr VARCHAR(255), /* 주소 */
	business_phone VARCHAR2(14), /* 사업체 전화번호 */
	business_homepage VARCHAR2(255), /* 사업자 홈페이지 */
	grade CHAR(1) /* 등급(관리자용),G,B,A */
);
CREATE UNIQUE INDEX PK_business
	ON business (
		business_id ASC
	);
ALTER TABLE business
	ADD
		CONSTRAINT PK_business
		PRIMARY KEY (
			business_id
		);

INSERT INTO BUSINESS VALUES('petlover', 'lover59', '134-12-55161', '애견이좋아', '57904/전라남도 순천시 서면 판교안길', '070-1531-4554', 'https://petlover.com', 'B');
INSERT INTO BUSINESS VALUES('cathost', 'cat77', '245-32-52143', '냥이호텔', '07906/서울특별시 양천구 화곡로12길', '010-0251-3254', 'https://cathost.com', 'B');
INSERT INTO BUSINESS VALUES('birdcompany', 'bird99', '114-54-12361', '조류병원', '52541/경상남도 사천시 서포면 외금로 197', '031-9751-1352', 'https://bird99.com', 'B');
INSERT INTO BUSINESS VALUES('marineanimal', 'marine', '434-12-25460', '바다동물원', '06288/서울특별시 강남구 삼성로 154 ', '070-5431-0364', 'https://marineanimal.com', 'B');
INSERT INTO BUSINESS VALUES('minipet', 'mini59', '224-12-55978', '미니펫', '49241/부산광역시 서구 까치고개로197번길', '070-3359-5959', 'https://minipet.com', 'B');
insert into business values('family', 'family408', '408-07-82530', '애견가족', '04056/서울특별시 마포구 신촌로', '010-2412-8956', 'www.dogfamily.co.kr', 'B');
insert into business values('chongpan', 'chongpan97', '105-09-71893', '애견용품총판', '02800/서울특별시 성북구 종암로6길', '02-376-4581', 'www.chongpan.net', 'B');
insert into business values ('pet38', 'dkssud3838', '124-15-99874', '애견38도씨', '35366/대전광역시 서구 계백로', '010-8567-3838', 'www.pet38do.com', 'B');
insert into business values ('garden', 'petgarden123', '568-10-32145', '펫가든 동물병원', '16514/경기도 수원시 영통구 월드컵로', '010-9874-1561', 'www.petgarden.com', 'B');
insert into business values ('story45', 'eoqkr12345', '214-66-41023', '펫과 나의 이야기', '26486/강원도 원주시 늘품로', '010-7412-5621', 'www.ourstory11.co.kr', 'B');
INSERT INTO BUSINESS VALUES ('tkdjqwk264','tkdjqwkdla1965','125-42-98462', '캣팡','11139/경기도 포천시 신북면 독곡길 30', '070-9762-4961','https://www.catpang.com/', 'B');   
INSERT INTO BUSINESS VALUES ('rhdid994','rhdiddlekd3512','156-75-35742', '미니펫','06136/서울 강남구 논현로106길 8', '02-5324-1482','https://minipetmall.co.kr/', 'B');  
INSERT INTO BUSINESS VALUES ('qnsdid34','qnsdidtm352','284-65-15651', '펫비투비','06727/서울특별시 서초구 서운로 45', '070-1565-1355','https://petbtob.co.kr/', 'B');  
INSERT INTO BUSINESS VALUES ('vmfhwpr46','dkakcndj0433','985-41-48565', '주세요닷컴','04089/서울특별시 마포구 독막로28길 10', '02-5648-6555', 'https://www.zooseyo.com/', 'B');  
INSERT INTO BUSINESS VALUES ('dbcldnjs78','cbfmtm415','423-41-95231', '도그모아','10314/경기도 고양시 일산동구 견달산로 360-31', '070-1564-2315','http://dogmoa.co.kr/', 'B');  
INSERT INTO business VALUES('modnh6847','password01','100-81-68471','모든하우스','21570/인천 남동구 예술로174번길 30','02-7842-6847','https://modnh6847.com', 'B');
INSERT INTO business VALUES('healthyfood','password01','150-87-84572','건강한사료','04331/서울 용산구 후암로 20 1층','032-4567-8344','https://healthyfood.com', 'B');
INSERT INTO business VALUES('petlife','password01','180-86-98455','펫라이프','04333/서울 용산구 두텁바위로1길 69-1','02-7841-7861','https://petlife.com', 'B');
INSERT INTO business VALUES('forthef','password01','189-81-74921','포더패밀리','04321/서울 용산구 한강대로 273','02-9458-7168','https://forthef.com', 'B');
INSERT INTO business VALUES('mmheaven','password01','600-87-83429','멍멍헤븐','48294/부산 수영구 수영로 592','051-4297-3457','https://mmheaven.com', 'B');
insert into business values('AnimalFood', 'ansfoodlocal123!', '252-11-52236', '반려동물 행복푸드', '24205/강원도 춘천시 신북읍 율문리9', '010-2132-9932', 'http://www.AnimalFood.com' , 'B');
insert into business values('HotelAnimals', 'bestdriver00!', '255-27-72439', '행복하고 즐거운 동물호텔', '06023/	서울특별시 강남구 언주로167길 21', '010-3514-5472', 'http://www.HotelAnimals.com', 'B' );
insert into business values('hospitality', 'makeesense', '271-23-89021', '종합 동물 병원', '01819/서울특별시 노원구 공릉로 156', '010-7315-9250', 'http://www.EmalgencyAnimalRescue.com' , 'B');
insert into business values('squllers', 'machamp210', '312-42-20153', '애완동물 용구점', '04213/	서울특별시 마포구 마포대로 114', '010-9915-7121', 'http://www.PetEquipmentStore.com' , 'B');
insert into business values('AnimalSalon', 'sleepingbeauty33!', '249-32-90182', '반려동물의 美', '08589/서울특별시 금천구 가산디지털1로 91', '010-1502-3952', 'http://www.AnimalSalon.com' , 'B');
insert into business values('varram', 'varram', '548-12-96321', 'VARRAM', '08503/서울특별시 금천구 가산디지털1로 171 가산 SK V1센터 401호', '010-8797-8923', 'https://www.varram.co.kr', 'B');
insert into business values('lovecoco', 'lovecoco', '456-13-45698', '러브코코', '10303/경기도 고양시 일산동구 풍동 1236-6 1층 101호', '010-2354-9854', 'https://lovecoco.co.kr', 'B');
insert into business values('seoulyes', 'yes24', '142-07-19331', '서울YES동물병원', '16875/경기도 용인시 수지구 죽전동 1174-1번지', '010-1272-5757', 'http://www.seoulyes.co.kr', 'B');
insert into business values('storebom', 'bom2456', '829-81-00792', '스토어봄', '06160/서울특별시 강남구 선릉로92길 42 세진빌딩 3층', '010-1544-9667', 'http://freshbom.kr', 'B');
insert into business values('loveanimal', 'love5578', '105-21-42009', '참사랑동물병원 ', '04156/서울특별시 마포구 독막로 331 마스터즈타워빌딩 1305호', '010-6959-9155', 'http://www.animaldoctor.co.kr', 'B');
insert into business values('catholic', 'holic123', '814-33-00732', '캣홀릭 ', '11625/경기도 의정부시 범골로 104 다모아빌딩 302호', '010-1354-4430', 'https://catholicat.modoo.at', 'B');
insert into business values('topcare', 'top123', '128-21-59895', '탑케어 동물의료원', '10500/경기도 고양시 덕양구 화신로272번길 5', '010-8220-3211', 'http://www.topcare.kr', 'B');
insert into business values('pethooh', 'hoohhooh', '404-87-01406', '펫후 ', '08503/경기도 성남시 분당구 판교로 242 A동 702호', '010-1577-2568', 'https://www.pethooh.com', 'B');
insert into business values('royalcanin', 'royalcanin123', '240-35-14624', '로얄캐닌', '06171/서울 강남구 테헤란로113길 12/한국감정원빌딩7층', '080-0410-5161', 'https://www.royalcanin.com/kr', 'B');
insert into business values('catskingdom', 'catskingdom123', '394-81-01000', '고양이 왕국', '10849/경기 파주시 평화로 266-11', '070-1688-7079', 'https://www.catskingdom.co.kr/', 'B');
insert into business values('drdog', 'drdog123', '156-15-48014', '닥터독', '04799/서울 성동구 아차산로15길 52/902호', '070-1544-0322', 'https://okdoctordog.com/index.html', 'B');
insert into business values('daengtopia', 'daengtopia123', '113-25-65001', '댕토피아 ', '12940/경기 하남시 신평로168번길 10', '010-6347-5553', 'http://daengtopia.com/', 'B');

============================================================================================

/*일반회원 테이블*/
CREATE TABLE member (
	member_id VARCHAR2(30) NOT NULL, /* 아이디 */
	member_pw VARCHAR2(30), /* 비밀번호 */
	member_addr VARCHAR(255), /* 주소 */
	member_email VARCHAR(40), /* 이메일 */
	member_mobile VARCHAR2(13), /* 휴대폰 */
	member_birth VARCHAR2(10), /* 생년월일 */
	point NUMBER, /* 마일리지 */
	grade CHAR(1), /* 등급(관리자용),G */
	sex CHAR(1) /* 성별 */
);

CREATE UNIQUE INDEX PK_member
	ON member (
		member_id ASC
	);

ALTER TABLE member
	ADD
		CONSTRAINT PK_member
		PRIMARY KEY (
			member_id
		);
INSERT INTO MEMBER VALUES('sihun9393', '654654','26427/강원도 원주시 무실로86번길', 'sihun9393@gamil.com', '1522-5457', '1993-12-10', 5000,'G', 'M');
INSERT INTO MEMBER VALUES('qorwlgml2003', 's8754','22821/인천광역시 서구 열우물로282번길', 'qorwlgml2003@naver.com', '5548-2231', '1997-06-13', 0,'G', 'F');
INSERT INTO MEMBER VALUES('okoo', '87548754','34364/대전광역시 대덕구 대화로32번길', 'okoo@gamil.com', '5646-7014', '2000-03-30', 0,'G', 'F');
INSERT INTO MEMBER VALUES('lalavla', 'lala77','17077/경기도 용인시 기흥구 중부대로788번길', 'kaka@daum.com', '2431-5574', '1999-02-01', 10000,'G', 'M');
INSERT INTO MEMBER VALUES('room93', 'no93','13466/경기도 성남시 분당구 운중로146번길', 'sihun2003@naver.com', '4464-9556', '2002-01-24', 5000,'G', 'M');
insert into member values('Konge12', '1w2e3r4t', '03175/서울특별시 종로구 경희궁2길', 'Konge12@naver.com', '1278-4465', '1991-02-13', 350, 'G', 'M');
insert into member values('angel33', 'angel1004', '22231/인천광역시 미추홀구 미추홀대로', 'angel@hanmail.net', '2256-8841', '1999-08-10', 410, 'G', 'F');
insert into member values('Hong0505', 'gildong1988', '61402/광주광역시 동구 필문대로', 'Hong0505@naver.com', '3674-1047', '1988-0505', 500, 'G', 'F');
insert into member values('snssnskssk', 'ckdmsdn', '48499/부산광역시 남구 용소로', 'snssnskssk@gmail.com', '5892-1142', '2001-09-11', 10400, 'G', 'F');
insert into member values('Rhdemr2', 'wjdwogus11', '28789/충청북도 청주시 상당구 무심동로', 'Rhdemr2@gmail.com', '7423-6910', '1995-11-03', 2200, 'G', 'M');
INSERT INTO MEMBER VALUES ('dkssud057', 'dkssudgktpdy251', '63166/제주특별자치도 제주시 칠성로길 5-3','dkssud057@naver.com', '4256-1571', '1984-05-16', 50000, 'G', 'M');  
INSERT INTO MEMBER VALUES ('glffld42', 'qlqjsdlek7842', '50035/경상남도 함양군 함양읍 상림1길 6','glffld42@naver.com', '8551-0654', '1994-11-03', 28000, 'G', 'F');  
INSERT INTO MEMBER VALUES ('wkqkdla35', 'dhfkzmf2688', '22314/인천광역시 중구 제물량로218번길 3','wkqkdla35@gmail.com', '1557-3208', '1978-02-17', 8000, 'G', 'F');  
INSERT INTO MEMBER VALUES ('rkddkwl963', 'rhdiddl7536', '14566/경기도 부천시 부천로136번길 9','rkddkwl963@gmail.com', '9634-0147', '1999-12-30', 65000, 'G', 'F');  
INSERT INTO MEMBER VALUES ('rhkwpdla852', 'qorhvk489', '25419/강원도 강릉시 주문진읍 해안2길 6-2','rhkwpdla852@naver.com', '8654-1537', '2002-07-10', 4000, 'G', 'M');  
INSERT INTO MEMBER VALUES('ghg1213','password01','22739/인천 서구 청라에메랄드로 112','ghg1213@daum.net','1234-9443','1998-12-13',0,'G','F');
INSERT INTO MEMBER VALUES('rlatjdwo1108','password01','21542/인천광역시 남동구 구월말로91번길 18-4','rlatjdwo1108@naver.com','3950-9443','1991-11-08',0,'G','M');
INSERT INTO MEMBER VALUES('money99','password01','05611/서울 송파구 백제고분로33길 32-14','money99@gmail.com','3950-1234','2002-05-05',0,'G','F');
INSERT INTO MEMBER VALUES('Joker1992','password01','04344/서울 용산구 회나무로21길 7','Joker1992@naver.com','5677-4457','1992-07-21',0,'G','M');
INSERT INTO MEMBER VALUES('Baobab10up','password01','48004/부산 해운대구 반송로916번길 10','Baobab10up@naver.com','9858-4258','1970-08-16',0,'G','M');
insert into member values('counter', 'upside1!', '13952/경기도 안양시 동안구 관악대로234', 'counter@gmail.com', '9245-3991', '1991-04-02', 0, 'G', 'M');
insert into member values('engage', 'engagement123!', '05279/서울특별시 강동구 구천면로 557 (상일동, 명일중앙하이츠아파트)', 'engage@gmail.com', '3513-7259', '1990-06-13', 0, 'G', 'F');
insert into member values('basecons2', 'wcvacation2', '17778/경기도 평택시 탄현로 96 (서정동, 신유빌리지2차)', 'free22@gmail.com', '2531-5612', '1994-11-23', 0, 'G', 'M');
insert into member values('makkacoin', 'skyroll11!!', '48502/부산광역시 남구 유엔로157번가길 83 (대연동, 레미안)', 'busansky@gmail.com', '5311-8920', '1988-02-21', 0, 'G', 'M');
insert into member values('cutecat', 'somegap25', '04399/서울특별시 용산구 한남대로27길 60 (한남동, 한남해피트리)', 'animalcats@gmail.com', '8985-7224', '1997-05-08', 0, 'G', 'F');
insert into member values('admin', '1234', '03923/서울 마포구 월드컵북로 424 LG CNS 상암IT 센터', 'sihun93@gmail.com', '3950-9443', '1993-12-10', 0, 'A', 'M');
============================================================================================

/* 테이터센터 데이터 테이블 */
CREATE TABLE DATACENTER (
    member_id VARCHAR2(30) primary key, 
	category1_count NUMBER(10),
	category1_date DATE,
	category2_count NUMBER(10), 
	category2_date date,
	category3_count NUMBER(10),
	category3_date date,
	category4_count NUMBER(10),
	category4_date date,
	CHECK_MONTH CHAR(1)
);



INSERT INTO DATACENTER VALUES('sihun9393', 5, sysdate, 1, sysdate, 1, sysdate, 1, sysdate, 'n');
INSERT INTO DATACENTER VALUES('Konge12', 1, sysdate, 1,sysdate, 4, sysdate, 2, sysdate, 'n');
INSERT INTO DATACENTER VALUES('rhkwpdla852', 2, sysdate, 2,sysdate, 1, sysdate, 3, sysdate, 'n');
INSERT INTO DATACENTER VALUES('wkqkdla35', 5, sysdate, 2,sysdate, 3, sysdate, 4, sysdate, 'n');
INSERT INTO DATACENTER VALUES('Baobab10up', 1, sysdate, 2,sysdate, 2, sysdate, 3, sysdate, 'n');
INSERT INTO DATACENTER VALUES('admin', 0, sysdate, 0,sysdate, 0, sysdate, 0, sysdate, 'n');
INSERT INTO DATACENTER VALUES('qorwlgml2003', 0, sysdate, 0,sysdate, 0, sysdate, 0, sysdate, 'n');
INSERT INTO DATACENTER VALUES('okoo', 0, sysdate, 0,sysdate, 0, sysdate, 0, sysdate, 'n'); 
INSERT INTO DATACENTER VALUES('lalavla',0, sysdate, 0,sysdate, 0, sysdate, 0, sysdate, 'n');
INSERT INTO DATACENTER VALUES('room93', 0, sysdate, 0,sysdate, 0, sysdate, 0, sysdate, 'n');
INSERT INTO DATACENTER VALUES('angel33', 0, sysdate, 0,sysdate, 0, sysdate, 0, sysdate, 'n');
INSERT INTO DATACENTER VALUES('Hong0505', 0, sysdate, 0,sysdate, 0, sysdate, 0, sysdate, 'n');
INSERT INTO DATACENTER VALUES('snssnskssk', 0, sysdate, 0,sysdate, 0, sysdate, 0, sysdate, 'n');
INSERT INTO DATACENTER VALUES('Rhdemr2', 0, sysdate, 0,sysdate, 0, sysdate, 0, sysdate, 'n');
INSERT INTO DATACENTER VALUES('dkssud057', 0, sysdate, 0,sysdate, 0, sysdate, 0, sysdate, 'n');	
INSERT INTO DATACENTER VALUES('glffld42', 0, sysdate, 0,sysdate, 0, sysdate, 0, sysdate, 'n');
INSERT INTO DATACENTER VALUES('rkddkwl963', 0, sysdate, 0,sysdate, 0, sysdate, 0, sysdate, 'n');
INSERT INTO DATACENTER VALUES('ghg1213', 0, sysdate, 0,sysdate, 0, sysdate, 0, sysdate, 'n');
INSERT INTO DATACENTER VALUES('rlatjdwo1108', 0, sysdate, 0,sysdate, 0, sysdate, 0, sysdate, 'n');
INSERT INTO DATACENTER VALUES('money99', 0, sysdate, 0,sysdate, 0, sysdate, 0, sysdate, 'n');
INSERT INTO DATACENTER VALUES('Joker1992', 0, sysdate, 0,sysdate, 0, sysdate, 0, sysdate, 'n');
INSERT INTO DATACENTER VALUES('counter', 0, sysdate, 0,sysdate, 0, sysdate, 0, sysdate, 'n');
INSERT INTO DATACENTER VALUES('engage', 0, sysdate, 0,sysdate, 0, sysdate, 0, sysdate, 'n');
INSERT INTO DATACENTER VALUES('basecons2', 0, sysdate, 0,sysdate, 0, sysdate, 0, sysdate, 'n');
INSERT INTO DATACENTER VALUES('makkacoin', 0, sysdate, 0,sysdate, 0, sysdate, 0, sysdate, 'n');
INSERT INTO DATACENTER VALUES('cutecat', 0, sysdate, 0,sysdate, 0, sysdate, 0, sysdate, 'n');

=================================================================================
