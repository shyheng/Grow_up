from django.contrib import admin
from django.urls import path

from App import views

urlpatterns = [
    path('shy/',views.shy,name='shy'),
]
