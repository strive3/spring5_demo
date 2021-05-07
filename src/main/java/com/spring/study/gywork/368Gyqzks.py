#!/usr/bin/python
# -*- coding: UTF-8 -*-
 

def doWork( a, b, c):
   	value = a * b + c
	print "运算结果： ", value
   	if value < 500:
   		print "小于500"
	if value < 1000:
   		print "小于1000"
	if value > 1000:
   		print "大于1000"
	if value < 0:
   		print "负数！"
   	return
 

doWork( a=11, b=22, c=233)
