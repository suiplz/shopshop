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

User Page
주문한 아이템, 좋아요한 아이템, 작성한 리뷰, 작성한 Q&A 보드, 멤버 정보 변경

Provider Page
등록한 아이템 -> item modify, remove
, item manage(구현완료)

자신이 쓴 글 자신만 지우기 성공 -> comment랑 board도 변경하기 -완-

Orders -> OrdersHistory
OrdersManage에서 주문 취소 or 주문 완료
Orders -> (OrdersStatus = 취소 or 완료 설정) -> Orders에서 삭제, 데이터 그대로 OrdersHistory에 insert
-완-

권한 처리
권한 바꾸기는 완료
권한에 따른 접근 provider -> itemRegister, itemDelete myItem manager -> delete all admin -> 회원 정보 접근 제외한 모든 기능?
member -> memberDelete?
provider 권한, manager권한 -> item 삭제 로직 -> itemImage, content, board 등 연관관계 삭제

Commit하기 전에 apikey, secretkey 변경 필수

memberId 받는 리스트에서 페이지 처리

결제, 환불 완료
orders -> ordersItem 변경하고 orders 전체 삭제
int amount = paymentService.paymentInfo(ordersItem.getImpUid(), token); 대신
int amount = ordersItemRepository.sumByImpUid(ordersItem.getImpUid());
안전한가? 대안은?

Item 제거시 OrdersItem, OrdersItemHistory까지 삭제해야하는 상황
Item 제거 기능 X -> 또 별도의 enum으로 관리?

남은거
권한처리
자신이 올린 리뷰-완- 보드, 코멘트, 좋아요 판매자 경우 아이템-완-
삭제 로직, 아이템 판매중지1\
payment amount issue
A 100원 B 50원
A 취소 후  B 취소 -> amount 150 grandTotal 150 ordersPrice 100 -> amount 50 grandTotal 50 ordersPrice 50 -> 문제 X
A 완료 후 B 취소 -> amount 150 granTotal 150 ordersPrice 100 -> amount 150 granTotal 50 ordersPrice 50 -> 문제 

a3 b2 c1

a완료
6 6 3 -> 6 3 2

일단 취소 취소 O
취소 완료 O
완료 취소 X -> 이경우 iamport 직접 들어가서 환불 -완- (checkSum해제하면서 완료 취소 가능, 보안 고민해볼것)

할인, 적립, 주소

할인 로직 
1. 가격 * (100 - (int) 할인율) 할인율 0~100
2. 원래가격에 할인율 적용한 할인가격 column? 화면에만 출력?
2.1 -> item modify시 할인가격 column 변경 가능
2.2 -> 할인율 변경시 자동으로 화면 변경 가능
3.1 
3.2 cartList에서 결제 시 calculate에서 적용가능
할인 적립
   적립 -> 결제 할 때 금액 일부분 포인트로 들어가도록 로직 완료
   ordersRegister에서 point 수정할 때 가격의 일부 감안하면 될듯
   포인트가 주문금액 넘어설 경우 아직 작동 안됨 마무리할것
# CRUD / Tesgt

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
