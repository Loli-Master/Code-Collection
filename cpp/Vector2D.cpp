#include<bits/stdc++.h>
using namespace std;

//later be Vector2D.hpp
class Vector2D {
  public:
	float X;
	float Y;

	//Vector Zero
	static Vector2D Zero;

	//Standard Init (0,0)
	Vector2D();

	//Init w/Assigment
	Vector2D(float x, float y);

	//Init by copy
	Vector2D(const Vector2D& source);

	//Vector Magnitude
	static float Magnitude(const Vector2D& source);

	//Vector DotProduct
	static float DotProduct(const Vector2D& left, const Vector2D& right);

	//Angle between Vectors in Degree
	static float Angle(const Vector2D& origin, const Vector2D& target);

	//Vector Angle (by cartesian or screen position) in Degree
	static float Angle(const Vector2D& target, bool cartesian);
};

//Nearly same for float
bool FEqual(float A, float B, int maxUlps);

//overload unary negative
Vector2D operator-(const Vector2D& right);

//overload +=
Vector2D& operator+=(Vector2D& left, const Vector2D& right);

//overload -=
Vector2D& operator-=(Vector2D& left, const Vector2D& right);

//overload +
Vector2D operator+(const Vector2D& left, const Vector2D& right);

//overload -
Vector2D operator-(const Vector2D& left, const Vector2D& right);

//overload *
Vector2D operator*(const Vector2D& left, float right);

//overload *
Vector2D operator*(float left, const Vector2D& right);

//overload *=
Vector2D& operator*=(Vector2D& left, float right);

//overload /
Vector2D operator/(const Vector2D& left, float right);

//overload /=
Vector2D& operator/=(Vector2D& left, float right);

//overload ==
bool operator==(const Vector2D& left, const Vector2D& right);

//overload !=
bool operator!=(const Vector2D& left, const Vector2D& right);

//later #include "Vector2D.inl"
//end of Vector2D.hpp

//later be Vector2D.inl
inline bool FEqual(float A, float B, int maxUlps = 4) {
	assert(maxUlps > 0 && maxUlps < 4 * 1024 * 1024);
	int aInt = *(int*)&A;
	if (aInt < 0)
		aInt = 0x80000000 - aInt;
	int bInt = *(int*)&B;
	if (bInt < 0)
		bInt = 0x80000000 - bInt;
	int intDiff = abs(aInt - bInt);
	if (intDiff <= maxUlps)
		return true;
	return false;
}

inline Vector2D::Vector2D():X(0),Y(0) {
}

inline Vector2D::Vector2D(float x, float y):X(x),Y(y) {
}

inline Vector2D::Vector2D(const Vector2D& source):X(source.X),Y(source.Y) {
}

inline Vector2D operator-(const Vector2D& right) {
	return Vector2D(-right.X, -right.Y);
}

inline Vector2D& operator+=(Vector2D& left, const Vector2D& right) {
	left.X += right.X;
	left.Y += right.Y;

	return left;
}

inline Vector2D& operator-=(Vector2D& left, const Vector2D& right) {
	left.X -= right.X;
	left.Y -= right.Y;

	return left;
}

inline Vector2D operator+(const Vector2D& left, const Vector2D& right) {
	return Vector2D(left.X + right.X, left.Y + right.Y);
}

inline Vector2D operator-(const Vector2D& left, const Vector2D& right) {
	return Vector2D(left.X - right.X, left.Y - right.Y);
}

inline Vector2D operator*(const Vector2D& left, float right) {
	return Vector2D(left.X * right, left.Y * right);
}

inline Vector2D operator*(float left, const Vector2D& right) {
	return Vector2D(right.X * left, right.Y * left);
}

inline Vector2D& operator*=(Vector2D& left, float right) {
	left.X *= right;
	left.Y *= right;

	return left;
}

inline Vector2D operator/(const Vector2D& left, float right) {
	return Vector2D(left.X / right, left.Y / right);
}

inline Vector2D& operator/=(Vector2D& left, float right) {
	left.X /= right;
	left.Y /= right;

	return left;
}

inline bool operator==(const Vector2D& left, const Vector2D& right) {
	return FEqual(left.X, right.X) && FEqual(left.Y, right.Y);
}

inline bool operator!=(const Vector2D& left, const Vector2D& right) {
	return (!FEqual(left.X, right.X)) || (!FEqual(left.Y, right.Y));
}

inline float Vector2D::Magnitude(const Vector2D& source) {
	return sqrt(source.X * source.X + source.Y * source.Y);
}

inline float Vector2D::DotProduct(const Vector2D& left, const Vector2D& right) {
	return left.X * right.X + left.Y * right.Y;
}

inline float Vector2D::Angle(const Vector2D& origin, const Vector2D& target) {
	return (acos(min(max((DotProduct(origin, target)) / (Magnitude(origin) * Magnitude(target)), (float) -1.0), (float) 1.0))
	/ 3.14159265358979) * 180.0 * (signbit(origin.X * target.Y - origin.Y * target.X)?-1.0:1.0);
}

inline float Vector2D::Angle(const Vector2D& target, bool cartesian) {
    if(cartesian){
        return Angle(Vector2D(0, 1), target);
    }else{
		return Angle(Vector2D(0, -1), target);
    }
}

Vector2D Vector2D::Zero(0,0);

//end of Vector2D.inl

int main() {
	Vector2D A = Vector2D::Zero;
	Vector2D B(1,1);
	B = -B;
	A += B * 2;
	A *= 3;
	B *= -1;
	cout<<"Vector A: "<<A.X<<" "<<A.Y<<endl;
	cout<<"Vector B: "<<B.X<<" "<<B.Y<<endl;
	cout<<"Vector A magnitude: "<<Vector2D::Magnitude(A)<<endl;
	cout<<"Vector B magnitude: "<<Vector2D::Magnitude(B)<<endl;
	cout<<"Vector A angle from north in cartesian: "<<Vector2D::Angle(A, true)<<endl;
	cout<<"Vector B angle from north in cartesian: "<<Vector2D::Angle(B, true)<<endl;
	cout<<"Vector A angle from north in screen: "<<Vector2D::Angle(A, false)<<endl;
	cout<<"Vector B angle from north in screen: "<<Vector2D::Angle(B, false)<<endl;
	cout<<"Angle from Vector A to Vector B: "<<Vector2D::Angle(A, B)<<endl;
	cout<<"Angle from Vector B to Vector A: "<<Vector2D::Angle(B, A)<<endl;
}
