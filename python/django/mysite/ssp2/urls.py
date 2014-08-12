from django.conf.urls import patterns, include, url

from django.contrib import admin
admin.autodiscover()
from ssp2 import views
urlpatterns = patterns('',
    # Examples:
    # url(r'^$', 'mysite.views.home', name='home'),
    # url(r'^blog/', include('blog.urls')),
    url(r'^index/',views.hello), 
    url(r'^$',views.hello), 
    url(r'^cookie/',views.cookie), 
)
