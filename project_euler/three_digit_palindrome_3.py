def reverseNum(n):
    digits = digit_count(n)
    reverse = 0
    for i in range(digits):
        digit = n % 10
        n = n / 10
        reverse = reverse * 10 + digit
    return reverse

# hack    
def digit_count(n):
    return len(str(n))

def is_palindrome(n):
    return n == reverseNum(n)

max_so_far = 0
for i in range(999, -1, -1):
    for j in range(999, -1, -1):
        product = i * j
        if (is_palindrome(product) and product > max_so_far):
            max_so_far = product
print max_so_far
