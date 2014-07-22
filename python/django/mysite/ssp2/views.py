from django.http import HttpResponse
from django.template import loader ,Context
from django.http import HttpResponse
def hello(request):
    return HttpResponse("Hello world2")

def cookie(request):
    if "my_cookie" in request.COOKIES:
	response= HttpResponse(request.COOKIES['my_cookie'])
    else:
	#response.set_cookie('my_cookie','cookie value')
	response= HttpResponse('test')
    	response.set_cookie('my_cookie','cookie value')
        #print request.COOKIES['csrftoken']
    return response
def getcookie(request):
    mycookie = request.COOKIES['my_cookie']
    csf = request.COOKIES['csrftoken']
    return HttpResponse("mycookie:%s,scf:%",mycookie,scf)
