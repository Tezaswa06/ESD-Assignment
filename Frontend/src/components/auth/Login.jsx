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
    const submitHandler = async (e) => {
        e.preventDefault()
        

        try {
             dispatch(setLoading(true))
            const res = await axios.post(`http://localhost:8080/api/auth/loginAdmin`, input, {
                headers: {
                    "Content-Type": "application/json"
                }
            })
            if (res.data.success) {
                dispatch(setUser(res.data.user))
                navigate("/")
                toast.success(res.data.message)
            }
        } catch (error) {
            console.log(error.response?.data);

        }finally{
            dispatch(setLoading(false))
        }

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
                    {/* <div className='flex justify-between items-center'>
                        <RadioGroup className='flex items-center gap-4 my-2'>
                            <div className="flex items-center space-x-2">
                                <Input
                               type='radio'
                               name='role'
                               checked={input.role==='student'}
                               onChange={changeEventHandler}
                               value='student'
                               className='cursor-pointer'
                                />
                                <Label htmlFor="option-one">Student</Label>
                            </div>
                            <div className="flex items-center space-x-2">
                            <Input
                                  type='radio'
                                  name='role'
                                  checked={input.role==='recuiter'}
                                  onChange={changeEventHandler}
                                  value='recuiter'
                                  className='cursor-pointer'
                                />
                                <Label htmlFor="option-two">Admin</Label>
                            </div>
                        </RadioGroup>
                    </div> */}
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