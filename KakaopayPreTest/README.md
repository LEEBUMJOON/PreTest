# 카카오페이 사전과제 3 - 주택 금융 서비스 API 개발  

## 개발 환경  
- 기본 환경   
    - IDE: Eclipse  
    - OS: Window  
    - GIT  
- Server
    - Java8
    - Spring Boot 2.2.4
    - JPA
    - H2
    - Gradle
    - Junit5


- 접속 Base URI: `http://localhost:8080`


## 빌드(Eclipse ide 환경 )
 1. Git Clone https://github.com/LEEBUMJOON/PreTest.git
 2. Gradle Project import
 3. Refresh Gradle proejct
 4. Build
 5. Run Spring Boot


## 기능 요구사항
### 필수사항
- 데이터 파일(`.csv`)에서 각 레코드를 데이터베이스에 저장하는 API 개발
- 주택금융 공급 금융기관(은행) 목록을 출력하는 API 개발
- 년도별 각 금융기관의 지원금액 합계를 출력하는 API 개발
    - 아래는 출력 예시

```
{
    “name”:”주택금융 공급현황”,
    [
        {   “year”: "2004년”,
            “total_amount”: 14145,
            “detail_amount”: {“주택도시기금”: 2143,”국민은행”: 4356,”우리은행”: 5342,...,”기타은행”: 1324},
        }
        {   “year”: "2005년”,
            “total_amount”: 23145,
            “detail_amount”: {“주택도시기금”: 1243,”국민은행”: 5336,”우리은행”: 4849,...,”기타은행”: 1093},
        }

        ...

        {   “year”: "2017년”,
            “total_amount”: 33145,
            “detail_amount”: {“주택도시기금”: 2240,”국민은행”: 4338,”우리은행”: 5131,...,”기타은행”: 1392}
        }
    ]
}
```

- 각 년도별 각 기관의 전체 지원 금액 중에서 가장 큰 금액의 기관명을 출력하는 API 개발
    - 예를들어, 2005년 ~ 2017년 중에 2010년 국민은행의 전체 지원금액(1월 ~ 12월 지원 합계)이 가장 높았다면 `{ “year": “2010” , "bank": “국민은행”}`을 결과로 출력한다.
    - 아래는 출력 예시

```
{
    “year": 2010 ,
    "bank": “국민은행”
}
```

- 전체 년도(2005 ~ 2016)에서 외환은행의 지원금액 평균 중에서 가장 작은 금액과 큰 금액을 출력하는 API 개발
    - 예를들어, 2005년 ~ 2016년 외환은행의 평균 지원금액(매년 12달의 지원금액 평균값)을 계산하여 가장 작은 값과 큰 값을 출력한다. 소수점 이하는 반올림해서 계산한다.
    - 아래는 출력 예시

```
{
    “bank”:”외환은행”,
    "support_amount”:
    [
        { ”year”: 2008 , ”amount”: 78},
        { ”year”: 2015 , ”amount”: 1702}
    ]
}
```




## 개발 제약사항
### 필수사항
- API 기능명세에서 기술된 API 를 모두 개발한다.
- 데이터 영속성 관리 및 매핑을 위한 ORM(Object Relational Mapping)을 사용하여 각 엔티티를 정의하고 레퍼지토리를 개발한다.  
    - 단, 엔티티 디자인은 지원자의 문제해결 방법에 따라 자유롭게 한다.
    - 단, 주택금융 공급기관은 독립 엔티티(기관명과 기관코드)로 디자인한다. `{“institute_name”, “institute_code”}`
- 단위 테스트 (Unit Test) 코드를 개발하여 각 기능을 검증한다.
- 모든 입/출력은 JSON 형태로 주고 받는다.
- 단, 각 API 에 HTTP Method 들(GET|POST|PUT|DEL)은 자유롭게 선택한다.


# 해결방법
## 1. 데이터 파일(`.csv`)에서 각 레코드를 데이터베이스에 저장하는 API 개발
## Request (/file/upload)  
- CSV 파일 요청을 받는 컨트롤러 구현
  - `/upload` URL 요청을 처리하는 `CsvFileUpLoadController` 클래스를 만듦
  -  `@RequestParam("file") MultipartFile multipartFile`을 사용하여 CSV 파일을 요청을 통해 받도록 함      
  -   MultipartFile을 사용 ( 참고 :  https://placeforjake.tistory.com/54 ) 
  
- CSV 파일 읽기
  - OpenCSV 라이브러리 (참고 : https://wildpup.cafe24.com/archives/82)
  - CSV 파일을 헤더와 데이터 부분으로 구분 하여 데이터 구조 생성       
  - 금액 의 콤마 구분자 ','는 replace 를 사용 제거 , 공백 제거 `TestUtil` 클래스 생성  
  - 헤러라인의 명칭을 코드로 생성하고 "(억원)" 문자를 제고 하고자 `InstitueInfo` Enum 클래스 생성   
  - 국민은행(억원) => name : 국민은행 , code : B01 으로 변환 저장 
    
- 엔티티(도메인)  
  - `Institute`, `Amount` 생성       
  - 두 엔티티의  JOIN KEY (institute_id)사용 
  

## 2. 주택금융 공급 금융기관(은행) 목록을 출력하는 API 개발
## Request(/InstiuteList)
- `Institut` 엔티티  `findAll()`하는 것  해결.

## 3. 년도별 각 금융기관의 지원금액 합계를 출력하는 API 개발  
##Request(/Instiute/Year)  
- `JpaRepository` `@Query` 어노테이션  `Native Query` 를 사용하여 해결.
- 년도별 기관 합계 List를 Map( year, intituteDetailTotalAmountDto ) 로 변경 저징.  (buildMapIntituteDetailTotalAmount )
- intituteTotalYearDto의 'Year' 를 key 로  Map( year, intituteDetailTotalAmountDto )에서 Get하여 연결
- 참고 : https://engkimbs.tistory.com/833


## 4. 각 년도별 각 기관의 전체 지원 금액 중에서 가장 큰 금액의 기관명을 출력하는 API 개발
## Request(/Instiute/Year/Max)
- `JpaRepository` `@Query` 어노테이션  `Native Query` 를 사용하여 해결.  
- 집계함수(sum)사용, 년도(year), 기관(name) 를 기준 group by. 
- 합계금을 기준으로 order by desc(내림 차순 정렬) List 첫번째 값을 return.


## 5. 전체 년도(2005 ~ 2016)에서 외환은행의 지원금액 평균 중에서 가장 작은 금액과 큰 금액을 출력하는 API 개발
## Request("/Instiute/Year/MinMax)  
 - `@RequestParam(value = "bankname")  
 - `JpaRepository` `@Query` 어노테이션  `Native Query` 를 사용하여 해결.    
 -  집계함수(Avg)사용, 년도(year), 기관_id(id) 를 기준 group by.
 -  집계함수(Avg) 기준으로 order by asc(오름차순 정렬) List 첫번째 값 min list.size()-1 값 max  
 

    
 

