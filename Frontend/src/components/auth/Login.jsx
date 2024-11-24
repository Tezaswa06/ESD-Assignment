import React from 'react'
import Navbar from '../shared/Navbar'
import { Label } from '../ui/label'
import { Input } from '../ui/input'
import { RadioGroup ,  RadioGroupItem} from '../ui/radio-group'
import { Link } from 'react-router-dom'
import { useState } from 'react'
import { Button } from '../ui/button'
import axios from 'axios'
import { useNavigate } from 'react-router-dom'
import { toast } from 'sonner'
import { USER_API_END_POINT } from '../../utils/constant'
import { useDispatch, useSelector } from 'react-redux'
import { setLoading, setUser } from '@/redux/authSlice'
import { Loader2 } from 'lucide-react'
const Login = () => {
    const [role,setRole] = useState('admin')
    const [input,setInput] = useState({
        adminEmail:"",
        password:"",
        
    })
    const {loading} = useSelector(store=>store.auth)
    const navigate = useNavigate()
    const dispatch = useDispatch()
    const changeEventHandler = (e)=>{
        setInput({...input,[e.target.name]:e.target.value})
      
    }
    
    const changeRoleHandler=(e)=>{
        setRole(e.target.value)
    }

    const submitHandler = async (e) => {
        e.preventDefault()
        
    if(role==='admin'){
        try {
            dispatch(setLoading(true));
    
            const res = await axios.post(
                `http://localhost:8080/api/auth/loginAdmin`,
                input
            );

            if(res.data.message=="Login Sucessfull"){
                dispatch(setUser(res.data.adminId))
                navigate("/")
                toast.success(res.data.message)
            }
    
            dispatch(setLoading(false));
            console.log(res.data);
            return res.data; // Handle the response
        } catch (error) {
            dispatch(setLoading(false));
            console.error("Error during login:", error.response?.data || error.message);
        }finally{
            dispatch(setLoading(false))
        }}

        if(role==='student'){
            try {
                dispatch(setLoading(true));

                const inp = {
                    studentEmail:adminEmail,
                    password:password
                }
        
                const res = await axios.post(
                    `http://localhost:8080/api/auth/loginStudent`,
                    inp
                );
        
                dispatch(setLoading(false));
                console.log(res.data);
                return res.data; // Handle the response
            } catch (error) {
                dispatch(setLoading(false));
                console.error("Error during login:", error.response?.data || error.message);
            }finally{
                dispatch(setLoading(false))
            }}

    }
    
    return (
        <div>
            <Navbar />
            <div  className='flex items-center justify-center max-w-7xl mx-auto'>
                <form  onSubmit={submitHandler} className='w-1/2 border border-gray-200 rounded-md p-4 my-10'>
                    <h1 className='font-bold text-xl mb-5'>Login</h1>
                   
                    <div className='my-2'>
                        <Label>Email</Label>
                        <Input
                           type="email"
                           value = {input.adminEmail}
                           name="adminEmail"
                           onChange={changeEventHandler}
                           placeholder="lavish@gmail.com"
                        />
                    </div>
                   
                    <div className='my-2'>
                        <Label>Password</Label>
                        <Input
                             type="password"
                             value = {input.password}
                             name="password"
                             onChange={changeEventHandler}
                        />
                    </div>
                 
                    {
                        loading? <Button><Loader2 className='mr-2 h-4 w-4 animate-spin'/>Please wait</Button> :
                        <Button type='submit' className='w-full my-4'>Login</Button>
                    }
                   
                    <span className='text-sm'>Dont have an account? <Link className='text-blue-600' to='/signup'>Signup</Link></span>
                </form>
            </div>
        </div>
    )
}

export default Login