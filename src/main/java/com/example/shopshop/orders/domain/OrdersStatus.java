package com.example.shopshop.orders.domain;

public enum OrdersStatus {

    배송준비중("READY"), 배송중("DELIVERY"), 배송완료("COMPLETE"), 취소요청("CANCEL REQUEST"), 취소("CANCEL");


    private String value;

    OrdersStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static OrdersStatus fromValue(String value) {
        for (OrdersStatus status : OrdersStatus.values()) {
            if (status.getValue().equals(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("찾을 수 없습니다.");
    }


}
