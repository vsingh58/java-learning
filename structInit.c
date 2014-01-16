#include <stdio.h>
typedef struct usb_store_st{
	int id;
	char * p;	
	} usb_store;

int main() {

char * p = "hello world";

/*Initialization 1: with a more intuitive programming model*/
usb_store usb_str = {
	.id = 10,
	.p = p
	};
printf("id = %d, name = %s\n", usb_str.id, usb_str.p);
/*Initialization 2: */
usb_store usb_str2 = { 2, "Welcome to Fedora"};
printf("id = %d, name = %s\n", usb_str2.id, usb_str2.p);
return 0;
}

/*
 * ISO/IEC 9899 - Programming languages - C.
 * http://www.open-std.org/jtc1/sc22/wg14/www/docs/n1124.pdf
 * page 138 - 139*
 * /
