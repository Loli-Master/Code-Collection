# Code-Collection
Just my code collection

| **File/Folder** | **Languange** |
| --- | --- |
| [SPLDV.cpp](#spldvcpp) | C++ |
| [mergesort.cpp](#mergesortcpp) | C++ |
| [kalkulator](#kalkulator) | Java |
| [bankQueue](#bankqueue) | Java |

## [SPLDV.cpp](cpp/SPLDV.cpp)
Simple Linear equation with 2 variables solver
### Input Format:
Read 2 general form (*ax + by = c*)
```
a1 b1 c1
a2 b2 c2
```
## [mergesort.cpp](cpp/mergesort.cpp)
Sorting an array of integers with size < 1000
### Input Format:
Read integer N followed by N numbers of integer
```
N a1 a2 ... aN
```
## [kalkulator](java/kalkulator)
Simple scientific calculator in java with only 4 operators ( '\+' , '\-' , '\*' , '\/' ) with brackets ( '(' , ')' )
### Input Format:
Read input of string to calculate line by line
#### Example:
```
(2+3)
2*(3-4)
2.44*(64+6*6)
```
## [bankQueue](java/bankQueue)
One of my college tasks, processing file input ([Data.in](#datain-format)) for bank currency exchanges
> Data.in must be located in folder "bankQueue"<br>
> *actually it located in package folder* 
### Input Format:
```
<Local_Currency_Code>
<Number_Of_Foreign_Currencies>
for each foreign currency :
    <Currency_Code> <Sell_Rate> <Buy_Rate>
endfor
<Number_Of_Currencies_Bank_Stored>
for each foreign currency :
    <Currency_Code> <Stored>
endfor
```
#### Input Example:
```
IDR
2
USD 10000 9000
EUR 13000 12500
3
IDR 9000000
USD 2000
EUR 2300
```
### Data.in Format:
```
<BUY/SELL> <Amount> <Currency>
```
#### Data.in Example:
```
SELL 10 USD
BUY 10 EUR
SELL 5 EUR
BUY 10 USD
```
