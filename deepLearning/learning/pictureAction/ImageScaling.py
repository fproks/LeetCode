import cv2

image = cv2.imread("../picture/dog.jpg")
print(image.shape)
print("原图大小为 %d, %d" % (image.shape[0], image.shape[1]))
cv2.imshow("scalling",image)
reimage = cv2.resize(image, (0, 0), fx=1.5, fy=1.5, interpolation=cv2.INTER_LANCZOS4 )
cv2.imwrite("../picture/dog2.jpg",reimage)
cv2.imshow("scalling", reimage)
cv2.waitKey(0)
cv2.destroyAllWindows()
print("修改后图像大小: %d %d" % (reimage.shape[0], reimage.shape[1]))
