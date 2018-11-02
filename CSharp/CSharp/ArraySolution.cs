namespace CSharp
{
    public class ArraySolution
    {
        public bool IsMonotonic(int[] A)
        {
            if (A.Length <= 1) return true;
            bool desc = false;
            bool asc = false;
            int i = 0, j = 1;
            while (j < A.Length)
            {
                if (A[i] < A[j])asc = true;
                if(A[i]>A[j]) desc = true;
                j++;
                i++;
            }

            return !(desc && asc);
        }
    }
}