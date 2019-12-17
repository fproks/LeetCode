#include <stdio.h>
#include <malloc.h>

void printMatrix(int** matrix,int size){
    for (size_t i = 0; i < size; i++)
    {
        for (size_t j = 0; j < size; j++)
        {
            printf("%d \t",matrix[i][j]);
            /* code */
        }
        
        printf("\n");
        /* code */
    }
}

void Transposition(int** matrix,int n){
    for (size_t i = 0; i < n; i++)
    {
        for (size_t j = 0; j <=i; j++)
        {
           int tmp = matrix[i][j];
           matrix[i][j]=matrix[j][i];
           matrix[j][i]=tmp;
            /* code */
        }
        /* code */
    }
    
}
// 根据输入生成二维数组
int main(){

    int n =0;
    printf("输入矩阵大小 \n");
    scanf("%d", &n);
    int ** m =(int**) malloc(sizeof(int *)*n);

    for (int i=0;i<n;i++){
        m[i]=(int*) malloc(sizeof(int)* n);
        printf("input the %d row numbers ,split with space\n",i);
        for (size_t j = 0; j < n; j++)
        {
            scanf("%d",&m[i][j]);
        }
    }
    printf("-----------------\n");
    printMatrix(m,n);
    printf("transportaition\n");
    Transposition(m,n);
    printMatrix(m,n);

}