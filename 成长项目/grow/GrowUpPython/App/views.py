from django.http import HttpResponse, JsonResponse

# Create your views here.
def shy(request):
    return JsonResponse({"data":"zph"})