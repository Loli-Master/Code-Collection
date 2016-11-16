//program SPLDV
//mencari titik potong dari persamaan garis
#include<iostream>
#include<cmath>
using namespace std;

struct psng{
	//struct untuk simpan koefisien persamaan
	//ax + by = c
	long long a,b,c;
};

long long gcd(long long a, long long b){
	//fpb euclid
	if(b==0)return a;
	return gcd(b,a%b);
}

bool valid(psng p, psng q){
	//cek ada angka 0 atau nggak
	return (bool)(p.a&&p.b&&q.a&&q.b);
}

void bagi(long long a, long long b, long long& atas, long long& bawah){
	//pembagian (a/b) spesial :)
	//nilai akhir: atas=pembilang bawah=penyebut
	long long fpb=gcd(abs(b),abs(a));
	atas=a/fpb;
	bawah=b/fpb;
	if(bawah<0ll){
		bawah*=(-1);
		atas*=(-1);
	}
}

//array of struct dari 2 persamaan
psng dik[2];

int main(){
	long long tx,ty,tc,d,mx,mc,zy,zc,ay,by,ax,bx;
	bool err=false;
	//input
	for(int i=0;i<2;i++){
		printf("Masukkan angka a, b, c (a dan b bukan 0) dalam persamaan %d:\n",i+1);
		printf("ax + by = c\n");
		//simpan koefisien x dan y dan konstanta c di variabel sementara: tx,ty,tc
		scanf("%lld%lld%lld",&tx,&ty,&tc);
		printf("Persamaan %d: %lldx + %lldy = %lld\n\n",i+1,tx,ty,tc);
		//nilai x diharuskan positif agar mudah dihitung
		if(tx<0ll){
			tx*=(0-1);
			ty*=(0-1);
			tc*=(0-1);
		}
		//simpan nilai
		dik[i].a=tx;
		dik[i].b=ty;
		dik[i].c=tc;
	}
	//cek kevalidan input
	if(valid(dik[0],dik[1])){
		//samain x nya
		printf("Persamaan setelah disamakan koefisien x nya:\n");
		for(int i=0;i<2;i++){
			//d: bilangan yang harus dikalikan agar koefisien x yg sekarang
			//berubah menjadi kpk dari 2 koefisien x dalam persamaan
			d=dik[1-i].a/(gcd(abs(dik[0].a),abs(dik[1].a)));
			dik[i].a*=d;
			dik[i].b*=d;
			dik[i].c*=d;
			printf("%lldx + %lldy = %lld\n",dik[i].a,dik[i].b,dik[i].c);
		}
		//cari nilai y
		//eliminasi, karena koefisien x sama
		//misal:
		//ax - by =  c
		//ax - dy =  e
		//_____________-
		// (b-d)y =c-e
		
		//zy = (b-d)
		//zc = (c-e)
		zy=dik[0].b-dik[1].b;
		zc=dik[0].c-dik[1].c;
		//kalo ruas kanan nggak habis
		if(zy!=0){
			//nilai y = zc/zy
			//ay = pembilang y
			//by = penyebut y
			bagi(zc,zy,ay,by);
			//cari nilai x
			//subtitusi nilai y ke persamaan 1
			//misal:
			//pers. 1: ax + by = c
			//nilai y = d/e
			//subtitusi:
			//ax + bd/e = c (semua ruas kalikan dengan e)
			//aex + bd = ce
			//e*ax = e*c - bd
			
			//mx = e*a
			//mc = e*c - bd
			mx=dik[0].a*by;
			mc=dik[0].c*by-dik[0].b*ay;
			//nilai x = mc/mx
			//ax = pembilang x
			//bx = penyebut x
			bagi(mc,mx,ax,bx);
		}
		//kalo ruas kanan habis
		else{
			err=true;
		}
		//hasilnya
		if(!err){
			printf("\nHasil:\n");
			if(bx==1)printf("x = %lld\n",ax);
			else printf("x = %lld/%lld\n",ax,bx);
			if(by==1)printf("y = %lld\n",ay);
			else printf("y = %lld/%lld\n",ay,by);
		}
		else{
			if(zc==0)printf("\nx dan y indefinit\n");
			else printf("\nTidak ada x dan y yang memenuhi\n");
		}
	}
	//input tidak valid
	else{
		printf("Input tidak valid\n");
		printf("Kalau a atau b = 0, bisa hitung sendiri lah :)\n");
	}
}