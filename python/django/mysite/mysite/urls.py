from django.conf.urls import patterns, include, url

from django.contrib import admin
admin.autodiscover()
urlpatterns = patterns('',
    # Examples:
    # url(r'^$', 'mysite.views.home', name='home'),
    # url(r'^blog/', include('blog.urls')),
    url(r'^hello/','ssp.views.hello'), 
    url(r'^hello2/',include('ssp2.urls')), 
    url(r'^admin/', include(admin.site.urls)),
)
