# īī������ �������� 3 - ���� ���� ���� API ����  

## ���� ȯ��  
- �⺻ ȯ��   
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


- ���� Base URI: `http://localhost:8080`


## ����(Eclipse ide ȯ�� )
 1. Git Clone https://github.com/LEEBUMJOON/PreTest.git
 2. Gradle Project import
 3. Refresh Gradle proejct
 4. Build
 5. Run Spring Boot


## ��� �䱸����
### �ʼ�����
- ������ ����(`.csv`)���� �� ���ڵ带 �����ͺ��̽��� �����ϴ� API ����
- ���ñ��� ���� �������(����) ����� ����ϴ� API ����
- �⵵�� �� ��������� �����ݾ� �հ踦 ����ϴ� API ����
    - �Ʒ��� ��� ����

```
{
    ��name��:�����ñ��� ������Ȳ��,
    [
        {   ��year��: "2004�⡱,
            ��total_amount��: 14145,
            ��detail_amount��: {�����õ��ñ�ݡ�: 2143,���������ࡱ: 4356,���츮���ࡱ: 5342,...,����Ÿ���ࡱ: 1324},
        }
        {   ��year��: "2005�⡱,
            ��total_amount��: 23145,
            ��detail_amount��: {�����õ��ñ�ݡ�: 1243,���������ࡱ: 5336,���츮���ࡱ: 4849,...,����Ÿ���ࡱ: 1093},
        }

        ...

        {   ��year��: "2017�⡱,
            ��total_amount��: 33145,
            ��detail_amount��: {�����õ��ñ�ݡ�: 2240,���������ࡱ: 4338,���츮���ࡱ: 5131,...,����Ÿ���ࡱ: 1392}
        }
    ]
}
```

- �� �⵵�� �� ����� ��ü ���� �ݾ� �߿��� ���� ū �ݾ��� ������� ����ϴ� API ����
    - �������, 2005�� ~ 2017�� �߿� 2010�� ���������� ��ü �����ݾ�(1�� ~ 12�� ���� �հ�)�� ���� ���Ҵٸ� `{ ��year": ��2010�� , "bank": ���������ࡱ}`�� ����� ����Ѵ�.
    - �Ʒ��� ��� ����

```
{
    ��year": 2010 ,
    "bank": ���������ࡱ
}
```

- ��ü �⵵(2005 ~ 2016)���� ��ȯ������ �����ݾ� ��� �߿��� ���� ���� �ݾװ� ū �ݾ��� ����ϴ� API ����
    - �������, 2005�� ~ 2016�� ��ȯ������ ��� �����ݾ�(�ų� 12���� �����ݾ� ��հ�)�� ����Ͽ� ���� ���� ���� ū ���� ����Ѵ�. �Ҽ��� ���ϴ� �ݿø��ؼ� ����Ѵ�.
    - �Ʒ��� ��� ����

```
{
    ��bank��:����ȯ���ࡱ,
    "support_amount��:
    [
        { ��year��: 2008 , ��amount��: 78},
        { ��year��: 2015 , ��amount��: 1702}
    ]
}
```




## ���� �������
### �ʼ�����
- API ��ɸ����� ����� API �� ��� �����Ѵ�.
- ������ ���Ӽ� ���� �� ������ ���� ORM(Object Relational Mapping)�� ����Ͽ� �� ��ƼƼ�� �����ϰ� �������丮�� �����Ѵ�.  
    - ��, ��ƼƼ �������� �������� �����ذ� ����� ���� �����Ӱ� �Ѵ�.
    - ��, ���ñ��� ���ޱ���� ���� ��ƼƼ(������ ����ڵ�)�� �������Ѵ�. `{��institute_name��, ��institute_code��}`
- ���� �׽�Ʈ (Unit Test) �ڵ带 �����Ͽ� �� ����� �����Ѵ�.
- ��� ��/����� JSON ���·� �ְ� �޴´�.
- ��, �� API �� HTTP Method ��(GET|POST|PUT|DEL)�� �����Ӱ� �����Ѵ�.


# �ذ���
## 1. ������ ����(`.csv`)���� �� ���ڵ带 �����ͺ��̽��� �����ϴ� API ����
## Request (/file/upload)  
- CSV ���� ��û�� �޴� ��Ʈ�ѷ� ����
  - `/upload` URL ��û�� ó���ϴ� `CsvFileUpLoadController` Ŭ������ ����
  -  `@RequestParam("file") MultipartFile multipartFile`�� ����Ͽ� CSV ������ ��û�� ���� �޵��� ��      
  -   MultipartFile�� ��� ( ���� :  https://placeforjake.tistory.com/54 ) 
  
- CSV ���� �б�
  - OpenCSV ���̺귯�� (���� : https://wildpup.cafe24.com/archives/82)
  - CSV ������ ����� ������ �κ����� ���� �Ͽ� ������ ���� ����       
  - �ݾ� �� �޸� ������ ','�� replace �� ��� ���� , ���� ���� `TestUtil` Ŭ���� ����  
  - �췯������ ��Ī�� �ڵ�� �����ϰ� "(���)" ���ڸ� ���� �ϰ��� `InstitueInfo` Enum Ŭ���� ����   
  - ��������(���) => name : �������� , code : B01 ���� ��ȯ ���� 
    
- ��ƼƼ(������)  
  - `Institute`, `Amount` ����       
  - �� ��ƼƼ��  JOIN KEY (institute_id)��� 
  

## 2. ���ñ��� ���� �������(����) ����� ����ϴ� API ����
## Request(/InstiuteList)
- `Institut` ��ƼƼ  `findAll()`�ϴ� ��  �ذ�.

## 3. �⵵�� �� ��������� �����ݾ� �հ踦 ����ϴ� API ����  
##Request(/Instiute/Year)  
- `JpaRepository` `@Query` ������̼�  `Native Query` �� ����Ͽ� �ذ�.
- �⵵�� ��� �հ� List�� Map( year, intituteDetailTotalAmountDto ) �� ���� ��¡.  (buildMapIntituteDetailTotalAmount )
- intituteTotalYearDto�� 'Year' �� key ��  Map( year, intituteDetailTotalAmountDto )���� Get�Ͽ� ����
- ���� : https://engkimbs.tistory.com/833


## 4. �� �⵵�� �� ����� ��ü ���� �ݾ� �߿��� ���� ū �ݾ��� ������� ����ϴ� API ����
## Request(/Instiute/Year/Max)
- `JpaRepository` `@Query` ������̼�  `Native Query` �� ����Ͽ� �ذ�.  
- �����Լ�(sum)���, �⵵(year), ���(name) �� ���� group by. 
- �հ���� �������� order by desc(���� ���� ����) List ù��° ���� return.


## 5. ��ü �⵵(2005 ~ 2016)���� ��ȯ������ �����ݾ� ��� �߿��� ���� ���� �ݾװ� ū �ݾ��� ����ϴ� API ����
## Request("/Instiute/Year/MinMax)  
 - `@RequestParam(value = "bankname")  
 - `JpaRepository` `@Query` ������̼�  `Native Query` �� ����Ͽ� �ذ�.    
 -  �����Լ�(Avg)���, �⵵(year), ���_id(id) �� ���� group by.
 -  �����Լ�(Avg) �������� order by asc(�������� ����) List ù��° �� min list.size()-1 �� max  
 

    
 

