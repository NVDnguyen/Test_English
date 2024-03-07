create database Test_English_DB4
create table Topic( -- THPTQG TOIEC IELTS
	idTopic varchar(3) primary key,
	nameTopic nvarchar(50) check(len(nameTopic)>0),
	descriptionTp nvarchar(max), 
	imgTopic varchar(100)
);
create table TypeAccount( -- FREE Premium
	typeID varchar(20) primary key
);
create table Lessons( -- nằm trong phần mục lục ví dụ top 10 đề năm 2020
	idLesson varchar(10) primary key,
	imgLesson varchar(100),
	descriptionL nvarchar(max),
	idTopic varchar(3) references Topic(idTopic),	
	typeID varchar(20) references TypeAccount(typeID)
);
create table Test( -- 1 đề trong số 10 đề năm 2020
	idTest varchar(10) primary key,
	descriptionTs nvarchar(max),
	idLesson varchar(10) references Lessons(idLesson)
	
);
create table Questions(
	idQ INT PRIMARY KEY IDENTITY(1,1),
	descriptionQ nvarchar(max),-- đề bài
	answer1 nvarchar(150),
	answer2 nvarchar(150),
	answer3 nvarchar(150),
	answer4 nvarchar(150),
	CorrectAnswer nvarchar(150),
	idTest varchar(10) references Test(idTest)
);


-- chưa vần đến
CREATE TABLE UserAccount (
    idUser varchar(10) PRIMARY KEY,
    nameUser varchar(50),
    gmail varchar(50) UNIQUE,
    googleId varchar(50) UNIQUE,
    token varchar(255),
	typeID varchar(20) REFERENCES TypeAccount(typeID)
    -- Mật khẩu không cần lưu khi tích hợp với dịch vụ Google
    -- password varchar(50)
);
CREATE TABLE UserTestResponse (
    idResponse INT IDENTITY(1,1) PRIMARY KEY,
    idUser varchar(10) REFERENCES UserAccount(idUser),
    idTest varchar(10) REFERENCES Test(idTest),
    idQ varchar(10) REFERENCES Questions(idQ),
    userAnswer nvarchar(max),
    isCorrect bit,
    CONSTRAINT CK_UserTestResponse_UserAnswerNotEmpty CHECK (LEN(userAnswer) > 0)
);
  create table Rooms(
  idRoom int identity(1,1) primary key,
  codeRoom varchar(50) unique,
  creater varchar(50) references Account(userName),
  idTest varchar(10) references Test(idTest),
  active Bit default 0
  )
-- insert
INSERT INTO Topic (idTopic, nameTopic, descriptionTp, imgTopic) 
VALUES
('1', 'IELTS', N'Kỳ thi IELTS là bước quan trọng để đánh giá khả năng sử dụng tiếng Anh. Trang web của chúng tôi cung cấp bài kiểm tra IELTS chất lượng, giúp thí sinh rèn luyện và tự tin hơn trong việc đối mặt với thách thức của bài thi quốc tế này.', 'assets\images\ielts.jpg'),
('2', 'TOEIC', N'Kỳ thi TOEIC là cơ hội để kiểm tra kỹ năng sử dụng tiếng Anh trong môi trường làm việc. Trang web của chúng tôi mang đến những bài kiểm tra TOEIC đa dạng, giúp người học nâng cao khả năng ngôn ngữ và chuẩn bị tốt nhất cho kỳ thi quan trọng này.', 'assets\images\toiec2.jpg'),
('3', 'THPTQG', N'Kỳ thi Trung học Phổ thông Quốc gia (THPTQG) là cột mốc quyết định sự thành công của học sinh. Trang web của chúng tôi cung cấp bài kiểm tra THPTQG chất lượng, giúp họ ôn tập hiệu quả và tự tin hơn trước thách thức quan trọng này.', 'assets\images\thptqg.png');

insert into TypeAccount(typeID)
values
('Free'),
('Premium');
--https://zim.vn/bai-tap-multiple-choice-trong-ielts-listening-kem-dap-an-va-giai-thich
--https://ielts-fighter.com/tin-tuc/ielts-reading-multiple-choice_mt1583434534.html

INSERT INTO Lessons(idLesson, imgLesson, descriptionL, idTopic, typeID)
VALUES 
('1', '', N'Đề thi thử quốc gia năm 2022', '3',  NULL),
('2', '', N'Đề thi thử quốc gia năm 2023', '3',  NULL),

('3', '', N'Đề thi Toice năm 2022', '2',  NULL),
('4', '', N'Đề thi Toice năm 2023', '2',  NULL),

('5', '', N'Test Multiple Choice trong IELTS Listening', '1',  NULL),
('6', '', N'Test Multiple Choice trong IELTS Reading', '1',  NULL);
INSERT INTO Test (idTest, descriptionTs, idLesson)
VALUES 
-- thpt qg
('T1', N'Đề thi số 1', '1'),
('T2', N'Đề thi số 2', '1'),
('T3', N'Đề thi số 3', '1'),
('T4', N'Đề thi số 1', '2'),
('T5', N'Đề thi số 2', '2'),
('T6', N'Đề thi số 3', '2'),

-- toeic
('T7', N'Đề thi số 1', '3'),
('T8', N'Đề thi số 2', '3'),
('T9', N'Đề thi số 3', '3'),
('T10', N'Đề thi số 1', '4'),
('T11', N'Đề thi số 2', '4'),
('T12', N'Đề thi số 3', '4'),

-- ielts
('T13', N'Đề thi số 1', '5'),
('T14', N'Đề thi số 2', '5'),
('T15', N'Đề thi số 3', '5'),
('T16', N'Đề thi số 1', '6'),
('T17', N'Đề thi số 2', '6'),
('T18', N'Đề thi số 3', '6');

