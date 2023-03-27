###쇼핑몰 구현하기 프로젝트
### - SPRING BOOT, SPRING DATA JPA, MYSQL

## TO DO
### userService,Security, item by category, order Implement, review Implement, cart CRUD, Validation, Paging

### 연관관계 테이블 - 하나의 서비스에서 여러개의 엔티티 필요로 할 시 1. 그에 맞는 쿼리 생성 2. 여러 model의 repository 이용 3. 가짜 객체 생성 통합할 것


#TODO 
#cart 구현 성공 order 감안하여 로직 보강
#cart 링크, 페이지 구현

#item list에서 review 갯수, 평점
item list, read에서 like 처리

orders service 에서 delivery status 구현

board, comment 완성시키기

비완성 로직 구현 - 아이템에서 리뷰 단 사용자가 같은 아이템에서 리뷰 작성, board도 마찬가지
아이템에서 자신이 쓴 리뷰만 변경, 삭제 할 수 있도록 


Category Repository
find Category By ItemId;

find item By Category Component
- find item By Category.season
- find item By Category.clothType
- find item By Gender

To do For Category
- register Item category
- modify Item category
- 

category Test 추가로 필요
itemDetail, itemList 에서 category 정보 노출시키도록?
category 정보를 통한 itemList 페이지 -완-

category 완료, 
ItemName으로 item 검색하는 기능 만들기 -완-

user page user가 쓴 리뷰, Q&A, 좋아요, 구매 정보
provider의 경우 자신이 등록한 아이템 -완-
board read 구현
comment 구현
이후 like랑 getItemListByRating? -완-
page처리 완료, 공통 오류 핸들러만 하면 진짜 끝난다.

comment modal로?
백엔드에서는 @LoginCheck 프론트에서는 authentication.principal.member.id 로 조건?

likes 완료
OrdersHistory 별도의 테이블? OrdersStatus = Complete 상태에 따른 쿼리?
OrdersStatus 완료, 취소 일 때 Orders Entity -> OrdersHistory Entity 구매자 판매자 전부 넣을 수 있나?
결제


# CRUD / Test

### USER
- [ ] Join
- [ ] Login
- [ ] Modify User
- [ ] Get User Profile
- [ ] Delete User

### Item
- [ ] Add Item
- [ ] Get Item List
- [ ] Get Item Details
- [ ] Modify Item
- [ ] Delete Item

### Order
- [ ] Add Order
- [ ] Modify Order
- [ ] Delete Order

### Cart
- [ ] Add To Cart
- [ ] Delete From Cart

### Review
- [ ] Add Review
- [ ] Modify Review
- [ ] Delete Review

### Likes
- [ ] Add Likes
- [ ] Delete Likes

### Paging

### ImageFile

### Category

### User Role

# VIEW

### Header
-[ ] Main Page Link
-[ ] Category Link
-[ ] User Page Link



### Main Page
- [ ] Item Detail Link

### Item List Page (by Category)
- [ ] Item Detail Link & Likes



### Item Detail Page
- [ ] Order & Cart Link
- [ ] Reviews
- [ ] Likes

### Item Provide Page

### User Page
- [ ] Signup Page
- [ ] Signin Page

### Order Page
- [ ] 

### Cart Page

= [ ]
