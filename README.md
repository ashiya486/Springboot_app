# Springboot_app
workflow api testing:<br />
1 /home/register---register user<br />
2 /home/auth--get jwt token<br />
3 /home/user/{id}---view/update user info<br />
4 /home/user/loan----create loan<br />
5 register admin user and generate admin jwt token<br />
6 /home/admin/---view list of all users <br />
7 /home/admin/loan----view list of all loans <br />
8 /home/admin/loan/approve/{id}--approve loan for loan id,substitute "approve" for "reject" to reject loan<br />
9/home/admin/loan/filter/{filter}---filter loan based on predefind filter-{"approved","pending","rejected"}<br />

sample inputs
customer user:
{

    "name":"ruchita",

    "username":"ruchita321",

    "password":"ruchita@67890",

    "address":"delhi",

    "state":"delhi",

    "country":"india",

    "email":ruchitat46@google.com,

    "pan":"864iyg0976",

    "contactNo":"4036502601",

    "accountType":"current",

    "dob":"29/01/1990",

    "role":"ROLE_USER"

}
Admin user:
{

    "name":"rajat",

    "username":"rajat321",

    "password":"Rajat@67890",

    "address":" delhi",

    "state":"delhi",

    "country":"india",

    "email":rajat46@google.com,

    "pan":"567cvbnty5",

    "contactNo":"9352964937",

    "accountType":"manager",

    "dob":"06/10/1995",

    "role":"ROLE_ADMIN"

}
loan:
{

    "userId":"1",

    "loanType":"business",

    "amount":"200000",

    "date":"03/06/2017",

    "rateOfInterest":"7",

    "duration":"36"

}
