export interface Order {
    userId:number,
    productItems : [{
        productId:number,
        quantity:number
    }],
    shippingAddress:string,
    orderTotal:number
}

export interface OrderPlaced{
    orderId:number,
    created:Date,
    shippingAddress:string,
    status:string,
    total:number
}
