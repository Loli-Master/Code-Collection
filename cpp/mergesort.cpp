#include<bits/stdc++.h>
using namespace std;

int n,A[1000];

void conquer(int a, int b, int c, int d){
	int ki=0,ka=0,x=a;
	const int n1=b-a+1,n2=d-c+1;
	int L[n1],R[n2];
	for(int i=0;i<n1;i++){
		L[i]=A[i+a];
	}
	for(int i=0;i<n2;i++){
		R[i]=A[i+c];
	}
	while(ki<n1&&ka<n2){
		if(L[ki]<=R[ka]){
			A[x]=L[ki];
			ki++;
		}
		else{
			A[x]=R[ka];
			ka++;
		}
		x++;
	}
	while(ki<n1){
		A[x]=L[ki];
		ki++;
		x++;
	}
	while(ka<n2){
		A[x]=R[ka];
		ka++;
		x++;
	}
}

void divide(int left, int right){
	if(left!=right){
		int middle=(left+right)/2;
		divide(left,middle);
		divide(middle+1,right);
		conquer(left,middle,middle+1,right);
	}
}

int main(){
	cin>>n;
	for(int i=0;i<n;i++){
		cin>>A[i];
	}
	divide(0,n-1);
	for(int i=0;i<n;i++){
		cout<<A[i]<<" ";
	}
	cout<<endl;
}
