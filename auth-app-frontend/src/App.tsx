import './App.css'
import {Button} from "./components/ui/button"
import {Calendar} from "./components/ui/calendar.tsx";

function App() {

  return (
    <div>
      <h1 className={"text-3xl font-bold"}>Hello Auth Frontend App</h1>
        <Button variant={"outline"}>Click me</Button>
        <Calendar/>
    </div>
  )
}

export default App
