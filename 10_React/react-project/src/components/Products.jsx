import React from 'react'
import styled from 'styled-components';

const productItems = [{
    name: "삼성 TV",
    price: 10000,
    color: "블랙"
},{
    name: "엘지 냉장고",
    price: 30000,
    color: "베이지"
},{
    name: "애플 노트북",
    price: 50000,
    color: "그레이"
}]

const Table = styled.table`
    width: 100%;
    border-collapse: collapse;
`

const Tr = styled.tr`
    &:hover{
        background-color: #1a1a1a;
    }
`

const Th = styled.th`
    color: #ff8484;
    border: 1px solid #dddddd;
    padding: 12px;
`

const Td = styled.td`
    color: #f8ffb9;
    border: 1px solid #dddddd;
    padding: 12px;
    &:hover{
        font-weight: bold;
    }
`

const Products = () => {
    return (
        <Table>
            <thead>
                <tr>
                    <Th>제품명</Th>
                    <Th>가격</Th>
                    <Th>색상</Th>
                </tr>
            </thead>
            <tbody>
                {productItems.map((product) => 
                    <Tr key={product.name}>
                        <Td>{product.name}</Td>
                        <Td>{product.price}</Td>
                        <Td>{product.color}</Td>
                    </Tr>
                )}
            </tbody>
        </Table>
    )
}

export default Products;