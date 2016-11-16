
#define named(v) ##v + " = " << v
{
	int my_variable = 10 ;
	//std::out << "my_variable = " << my_variable << std::eol ;
	std::out << named(my_variable) << std::eol ;
}